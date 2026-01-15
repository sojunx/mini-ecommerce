package dev.sojunx.ecommerce.api.application.service.order;

import dev.sojunx.ecommerce.api.application.dto.command.PlaceOrderCommand;
import dev.sojunx.ecommerce.api.application.dto.query.OrderDetails;
import dev.sojunx.ecommerce.api.application.mapper.OrderMapper;
import dev.sojunx.ecommerce.api.domain.entities.order.Order;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.domain.enums.OrderStatus;
import dev.sojunx.ecommerce.api.infrastructure.repository.OrderRepository;
import dev.sojunx.ecommerce.api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repo;
    private final OrderMapper mapper;
    private final UserRepository userRepo;

    @Transactional
    public OrderDetails placeOrder(User user, PlaceOrderCommand command) {
        var existUser = userRepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        var items = existUser.getCart().getItems();
        if (items.isEmpty()) throw new RuntimeException("Cart is empty");

        var order = new Order();
        order.setUser(existUser);
        order.setPaymentMethod(command.getPaymentMethod());
        order.setStatus(OrderStatus.PENDING);

        var orderItems = items.stream().map(item -> mapper.toEntity(order, item)).toList();

        order.setItems(orderItems);
        var savedOrder = repo.save(order);

        // Delete all cart items
        existUser.getCart().getItems().clear();
        userRepo.save(existUser);

        return mapper.toDto(savedOrder);
    }
}
