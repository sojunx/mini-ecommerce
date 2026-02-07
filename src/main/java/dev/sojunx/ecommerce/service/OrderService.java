package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.Order;
import dev.sojunx.ecommerce.domain.entity.OrderItem;
import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.dto.request.OrderRequest;
import dev.sojunx.ecommerce.exception.NotFoundException;
import dev.sojunx.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        var saved = repository.save(order);

        request.getItems().forEach(item -> itemService.createOrderItem(item, saved.getId(), user.getId()));

        var items = itemService.getItemsByOrderId(saved.getId());
        var total = items.stream().mapToDouble(OrderItem::getTotal).sum();
        saved.setTotal(total);

        return repository.save(saved);
    }

    public Order getOrderById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("Order not found");

        return result.get();
    }

    public List<Order> findAllByUserId(UUID id) {
        return repository.findAllByUserId(id);
    }
}
