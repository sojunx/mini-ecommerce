package dev.sojunx.ecommerce.api.domain.entities.order;

import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.domain.entities.user.UserAddress;
import dev.sojunx.ecommerce.api.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class Order {
    private Long id;
    private User user;
    private UserAddress userAddress;

    private OrderStatus status;
    private double totalPrice;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
