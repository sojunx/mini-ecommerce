package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.domain.entity.Review;
import dev.sojunx.ecommerce.dto.ReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .email(review.getEmail())
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
