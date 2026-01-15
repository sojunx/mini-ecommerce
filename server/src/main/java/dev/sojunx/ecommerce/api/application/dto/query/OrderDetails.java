package dev.sojunx.ecommerce.api.application.dto.query;

import dev.sojunx.ecommerce.api.domain.entities.order.Order;
import dev.sojunx.ecommerce.api.domain.enums.OrderStatus;
import dev.sojunx.ecommerce.api.domain.enums.PaymentMethod;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderDetails {
    private UUID id;
    private OrderStatus status;
    private double totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;

    public OrderDetails(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.totalAmount = order.getTotalAmount();
        this.paymentMethod = order.getPaymentMethod();
        this.createdAt = order.getCreatedAt();
    }
}
