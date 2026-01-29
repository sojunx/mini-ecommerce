package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Order;
import dev.sojunx.ecommerce.domain.entity.OrderItem;
import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.domain.enums.OrderStatus;
import dev.sojunx.ecommerce.dto.request.OrderRequest;
import dev.sojunx.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderItemService itemService;
    private final UserService userService;

    @Transactional
    public Order createOrder(OrderRequest request) {
        User user = null;
        if (request.getUserId() != null)
            user = userService.getUserById(request.getUserId());

        var order = new Order();
        if (user != null) {
            order.setEmail(user.getEmail());
            order.setUserId(user.getId());
        } else
            order.setEmail(request.getEmail());

        var saved = repository.save(order);
        request.getItems().forEach(item -> itemService.createOrderItem(item, saved.getId()));

        var items = itemService.getItemsByOrderId(saved.getId());
        var total = items.stream().mapToDouble(OrderItem::getTotal).sum();
        saved.setTotal(total);

        return repository.save(saved);
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
