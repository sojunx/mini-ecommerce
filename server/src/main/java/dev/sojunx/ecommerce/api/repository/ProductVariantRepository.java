package dev.sojunx.ecommerce.api.repository;

import dev.sojunx.ecommerce.api.domain.entities.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
}
