package dev.sojunx.ecommerce.api.repository;

import dev.sojunx.ecommerce.api.domain.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
