package dev.sojunx.ecommerce.repository;

import dev.sojunx.ecommerce.domain.entity.Review;
import dev.sojunx.ecommerce.dto.RatingCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByProductId(UUID productId);

    Integer countReviewsByProductId(UUID productId);

    @Query("SELECT COALESCE(AVG(r.rating), 0.0) FROM Review r WHERE r.productId = :productId")
    Double averageRatingByProductId(@Param("productId") UUID productId);

    @Query("SELECT r.rating as rating, COUNT(r) as count FROM Review r WHERE r.productId = :productId GROUP BY r.rating ORDER BY r.rating")
    List<RatingCount> getRatingDistributionByProductId(@Param("productId") UUID productId);
}