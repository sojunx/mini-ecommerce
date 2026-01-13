package dev.sojunx.ecommerce.api.repository;

import dev.sojunx.ecommerce.api.domain.entities.cart.Cart;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
