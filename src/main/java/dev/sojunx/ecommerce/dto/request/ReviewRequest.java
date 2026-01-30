package dev.sojunx.ecommerce.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class ReviewRequest {
    private UUID orderId;
    private UUID productId;
    private String email;
    private String comment;
    private Integer rating;
}
