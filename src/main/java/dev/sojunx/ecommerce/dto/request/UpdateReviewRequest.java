package dev.sojunx.ecommerce.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateReviewRequest {
    private String comment;
    private Integer rating;
    private String email;
    private UUID userId;
}
