package dev.sojunx.ecommerce.api.application.dto.query;

import dev.sojunx.ecommerce.api.domain.entities.product.Review;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReviewDetails {
    private UUID id;

    private String fullName; // User name
    private int rating;
    private String title;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewDetails(Review review) {
        this.fullName = review.getUser().getFullName();
        this.id = review.getId();
        this.rating = review.getRating();
        this.title = review.getTitle();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
    }
}
