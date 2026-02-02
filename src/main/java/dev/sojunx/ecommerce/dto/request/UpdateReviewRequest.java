package dev.sojunx.ecommerce.dto.request;

import lombok.Data;

@Data
public class UpdateReviewRequest {
    private String comment;
    private Integer rating;
}
