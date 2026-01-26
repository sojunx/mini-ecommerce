package dev.sojunx.ecommerce.dto;

import dev.sojunx.ecommerce.domain.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OrderDto {
    private UUID id;
    private String email;
    private Double total;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
