package dev.sojunx.ecommerce.api.application.mapper;

import dev.sojunx.ecommerce.api.application.dto.command.AddReviewCommand;
import dev.sojunx.ecommerce.api.application.dto.query.ReviewDetails;
import dev.sojunx.ecommerce.api.domain.entities.product.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewDetails toDto(Review review) {
        return new ReviewDetails(review);
    }

    public Review toEntity(AddReviewCommand command) {
        var review = new Review();

        review.setTitle(command.getTitle());
        review.setComment(command.getComment());
        review.setRating(command.getRating());

        return review;
    }
}
