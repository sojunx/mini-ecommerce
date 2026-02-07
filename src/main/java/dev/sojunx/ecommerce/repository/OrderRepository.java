package dev.sojunx.ecommerce.repository;

import dev.sojunx.ecommerce.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> getOrderByUserId(UUID userId);

    List<Order> findAllByUserId(UUID userId);
}