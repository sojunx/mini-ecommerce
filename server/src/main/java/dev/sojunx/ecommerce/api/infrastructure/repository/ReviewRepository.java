package dev.sojunx.ecommerce.api.infrastructure.repository;

import dev.sojunx.ecommerce.api.domain.entities.product.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
