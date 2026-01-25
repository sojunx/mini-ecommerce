package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.dto.request.OrderRequest;
import dev.sojunx.ecommerce.dto.response.OrderDto;
import dev.sojunx.ecommerce.entity.User;
import dev.sojunx.ecommerce.mapper.OrderItemMapper;
import dev.sojunx.ecommerce.mapper.OrderMapper;
import dev.sojunx.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderItemMapper itemMapper;

    @Transactional
    public OrderDto createOrder(OrderRequest request, User user) {
        var order = mapper.toEntity(request);
        if (user != null) order.setUser(user);

        var items = request.getItems().stream().map(itemMapper::toEntity).toList();
        order.setItems(items);

        var saved = repository.save(order);
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public OrderDto getOrderById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new RuntimeException("Order not found with id: " + id);

        return mapper.toDto(result.get());
    }
}
