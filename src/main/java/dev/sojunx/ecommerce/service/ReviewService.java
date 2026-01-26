package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Review;
import dev.sojunx.ecommerce.domain.enums.OrderStatus;
import dev.sojunx.ecommerce.dto.ReviewRequest;
import dev.sojunx.ecommerce.dto.ReviewStats;
import dev.sojunx.ecommerce.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    private final OrderService orderService;
    private final OrderItemService itemService;

    @Transactional(readOnly = true)
    public List<Review> getReviews(UUID id) {
        return repository.findAllByProductId(id);
    }

    @Transactional
    public Review createReview(UUID id, ReviewRequest request) {
        var order = orderService.getOrderById(id);
        if (!order.getEmail().equals(request.getEmail()))
            throw new RuntimeException("You can't review this order");

        if (order.getStatus() != OrderStatus.COMPLETED)
            throw new RuntimeException("Order not completed");

        var item = itemService.getItemByOrderIdAndProductId(id, request.getProductId());
        if (item.isReviewed())
            throw new RuntimeException("Product already reviewed");

        var review = new Review();
        review.setEmail(request.getEmail());
        review.setProductId(item.getProductId());
        review.setOrderId(id);
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        item.setReviewed(true);
        itemService.saveItem(item);

        return repository.save(review);
    }

    @Transactional(readOnly = true)
    public ReviewStats getReviewStats(UUID id) {
        var total = repository.countReviewsByProductId(id);
        var avg = repository.averageRatingByProductId(id);

        return ReviewStats.builder()
                .total(total)
                .averageRating(avg)
                .build();
    }
}
