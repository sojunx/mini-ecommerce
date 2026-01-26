package dev.sojunx.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewStats {
    private Integer total;
    private Double averageRating;
}
