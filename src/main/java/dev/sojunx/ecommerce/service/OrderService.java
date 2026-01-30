package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Order;
import dev.sojunx.ecommerce.domain.entity.OrderItem;
import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.domain.enums.OrderStatus;
import dev.sojunx.ecommerce.dto.request.OrderRequest;
import dev.sojunx.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderItemService itemService;

    @Transactional
    public Order createOrder(OrderRequest request, User user) {
        var order = new Order();
        order.setEmail(user.getEmail());
        order.setUserId(user.getId());

        return updateTotal(request, repository.save(order));
    }

    @Transactional
    public Order createOrder(OrderRequest request) {
        var order = new Order();
        order.setEmail(request.getEmail());

        return updateTotal(request, repository.save(order));
    }

    @NonNull
    private Order updateTotal(OrderRequest request, Order order) {
        request.getItems().forEach(item -> itemService.createOrderItem(item, order.getId()));

        var items = itemService.getItemsByOrderId(order.getId());
        var total = items.stream().mapToDouble(OrderItem::getTotal).sum();
        order.setTotal(total);

        return repository.save(order);
    }
    
    public Order getOrderById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new RuntimeException("Order not found with id: " + id);

        return result.get();
    }

    public void updateOrderStatus(UUID id, OrderStatus status) {
        var order = getOrderById(id);
        order.setStatus(status);
        repository.save(order);
    }
}
