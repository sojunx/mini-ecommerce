package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.domain.entity.OrderItem;
import dev.sojunx.ecommerce.dto.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItemDto toDto(OrderItem item) {
        return OrderItemDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .total(item.getTotal())
                .build();
    }
}
