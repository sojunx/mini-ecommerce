package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Review;
import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.dto.request.ReviewRequest;
import dev.sojunx.ecommerce.dto.response.ReviewStats;
import dev.sojunx.ecommerce.mapper.ReviewMapper;
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
    private final ReviewMapper mapper;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<Review> getReviews(UUID id) {
        return repository.findAllByProductId(id);
    }

    @Transactional
    public Review createReview(UUID id, ReviewRequest request) {
        User user = null;
        if (request.getUserId() != null)
            user = userService.getUserById(request.getUserId());

        var order = orderService.getOrderById(id);

        var item = itemService.getItemByOrderIdAndProductId(id, request.getProductId());
        if (item.isReviewed())
            throw new RuntimeException("Product already reviewed");

        var review = new Review();
        if (user != null) {
            review.setEmail(user.getEmail());
            review.setUserId(user.getId());
        } else
            review.setEmail(request.getEmail());

        review.setProductId(item.getProductId());
        review.setOrderId(order.getId());
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
        var distribution = repository.getRatingDistributionByProductId(id);

        var result = distribution.stream().map(mapper::toDto).toList();
        return ReviewStats.builder()
                .total(total)
                .averageRating(avg)
                .ratingsCount(result)
                .build();
    }

    @Transactional
    public Review updateReview(UUID id) { return null; }

    @Transactional
    public void deleteReview(UUID id, UUID userId) {
        repository.deleteByIdAndUserId(id, userId);
    }
}
