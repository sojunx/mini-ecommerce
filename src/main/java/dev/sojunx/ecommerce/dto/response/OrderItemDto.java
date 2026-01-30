package dev.sojunx.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderItemDto {
    private Long id;
    private String name;
    private UUID productId;
    private Integer quantity;
    private Double price;
    private Double total;
    private boolean isReviewed;
}
