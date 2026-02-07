package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Review;
import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.dto.request.ReviewRequest;
import dev.sojunx.ecommerce.dto.request.UpdateReviewRequest;
import dev.sojunx.ecommerce.dto.response.ReviewStats;
import dev.sojunx.ecommerce.exception.InvalidException;
import dev.sojunx.ecommerce.exception.NotFoundException;
import dev.sojunx.ecommerce.mapper.ReviewMapper;
import dev.sojunx.ecommerce.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public Page<Review> findAll(UUID id, Integer rating, Pageable pageable) {
        if (rating != null)
            return repository.findAllByProductIdAndRating(id, rating, pageable);

        return repository.findAllByProductId(id, pageable);
    }

    @Transactional
    public Review createReview(ReviewRequest request, User user) {
        var order = orderService.getOrderById(request.getOrderId());
        if (!order.getUserId().equals(user.getId()))
            throw new InvalidException("Invalid user");

        var item = itemService.getItemByOrderIdAndProductId(request.getOrderId(), request.getProductId());
        if (item.isReviewed())
            throw new RuntimeException("Product already reviewed");

        var review = new Review();
        review.setEmail(user.getEmail());
        review.setUserId(user.getId());

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
    public Review updateReview(UUID id, UpdateReviewRequest request, UUID userId) {
        IO.println(userId);
        IO.println(request);
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("Review not found");

        var review = result.get();
        IO.println(review);
        if (!review.getUserId().equals(userId))
            throw new RuntimeException("User not authorized to update review");

        if (request.getRating() != null)
            review.setRating(request.getRating());

        if (request.getComment() != null)
            review.setComment(request.getComment());

        return repository.save(review);
    }

    @Transactional
    public void deleteReview(UUID id, UUID userId) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("Review not found");

        var review = result.get();
        if (!review.getUserId().equals(userId))
            throw new RuntimeException("User not authorized to delete review");

        repository.deleteById(id);
    }

    public List<Review> findAllByUserId(UUID id) {
        return repository.findAllByUserId(id);
    }
}
