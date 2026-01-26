package dev.sojunx.ecommerce.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReviewRequest {
    private UUID productId;
    private String email;
    private String comment;
    private Integer rating;
}
