package dev.sojunx.ecommerce.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteReviewRequest {
    private UUID userId;
    private String email;
}
