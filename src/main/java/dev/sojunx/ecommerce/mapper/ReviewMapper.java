package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.domain.entity.RatingCount;
import dev.sojunx.ecommerce.domain.entity.Review;
import dev.sojunx.ecommerce.dto.response.RatingCountDto;
import dev.sojunx.ecommerce.dto.response.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .productId(review.getProductId())
                .email(review.getEmail())
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public RatingCountDto toDto(RatingCount rating) {
        return RatingCountDto.builder()
                .rating(rating.getRating())
                .count(rating.getCount())
                .build();
    }
}
