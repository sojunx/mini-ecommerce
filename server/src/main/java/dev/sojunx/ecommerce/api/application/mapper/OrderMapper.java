package dev.sojunx.ecommerce.api.application.mapper;

import dev.sojunx.ecommerce.api.application.dto.query.OrderDetails;
import dev.sojunx.ecommerce.api.domain.entities.cart.CartItem;
import dev.sojunx.ecommerce.api.domain.entities.order.Order;
import dev.sojunx.ecommerce.api.domain.entities.order.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderItem toEntity(Order order, CartItem item) {
        var product = item.getProduct();

        var orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setQuantity(item.getQuantity());

        return orderItem;
    }

    public OrderDetails toDto(Order order) {
        return new OrderDetails(order);
    }
}
