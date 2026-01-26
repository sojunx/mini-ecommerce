package dev.sojunx.ecommerce.repository;

import dev.sojunx.ecommerce.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(UUID orderId);

    Optional<OrderItem> findByOrderIdAndProductId(UUID orderId, UUID productId);
}