package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.dto.request.OrderRequest;
import dev.sojunx.ecommerce.dto.response.OrderDto;
import dev.sojunx.ecommerce.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toEntity(OrderRequest request) {
        return Order.builder()
                .user(null)
                .fullName(request.getFullName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .fullName(order.getFullName())
                .email(order.getEmail())
                .address(order.getAddress())
                .phoneNumber(order.getPhoneNumber())
                .total(order.getTotal())
                .status(order.getStatus())
                .build();
    }
}
