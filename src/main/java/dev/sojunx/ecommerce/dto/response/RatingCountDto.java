package dev.sojunx.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingCountDto {
    private Integer rating;
    private Long count;
}
