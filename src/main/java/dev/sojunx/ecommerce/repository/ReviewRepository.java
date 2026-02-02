package dev.sojunx.ecommerce.repository;

import dev.sojunx.ecommerce.domain.entity.RatingCount;
import dev.sojunx.ecommerce.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Page<Review> findAllByProductId(UUID productId, Pageable pageable);

    Integer countReviewsByProductId(UUID productId);

    @Query("SELECT COALESCE(AVG(r.rating), 0.0) FROM Review r WHERE r.productId = :productId")
    Double averageRatingByProductId(@Param("productId") UUID productId);

    @Query("SELECT r.rating as rating, COUNT(r) as count FROM Review r WHERE r.productId = :productId GROUP BY r.rating ORDER BY r.rating")
    List<RatingCount> getRatingDistributionByProductId(@Param("productId") UUID productId);

    void deleteByIdAndUserId(UUID id, UUID userId);

    void deleteByIdAndEmail(UUID id, String email);

    boolean existsByUserIdAndProductId(UUID userId, UUID productId);
}