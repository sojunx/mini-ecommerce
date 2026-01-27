package dev.sojunx.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewStats {
    private Integer total;
    private Double averageRating;
    private List<RatingCountDto> ratingsCount;
}
