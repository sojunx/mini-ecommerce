package dev.sojunx.ecommerce.api.infrastructure.repository;

import dev.sojunx.ecommerce.api.domain.entities.product.Product;
import dev.sojunx.ecommerce.api.domain.entities.product.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByProduct(Product product);
}
