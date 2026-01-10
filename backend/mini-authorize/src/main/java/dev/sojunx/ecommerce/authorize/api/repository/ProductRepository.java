package dev.sojunx.ecommerce.authorize.api.repository;

import dev.sojunx.ecommerce.authorize.api.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
