package dev.sojunx.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderItemDto {
    private Long id;
    private UUID productId;
    private Integer quantity;
    private Double price;
    private Double total;
}
