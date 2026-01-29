package dev.sojunx.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ReviewDto {
    private UUID id;
    private String email;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;
}
