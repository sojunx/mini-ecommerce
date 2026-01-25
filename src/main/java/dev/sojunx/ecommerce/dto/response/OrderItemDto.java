package dev.sojunx.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderItemDto {
    private UUID productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Double total;
}
