package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.domain.entity.Order;
import dev.sojunx.ecommerce.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .email(order.getEmail())
                .total(order.getTotal())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
