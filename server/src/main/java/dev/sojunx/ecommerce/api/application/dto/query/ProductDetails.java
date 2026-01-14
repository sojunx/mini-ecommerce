package dev.sojunx.ecommerce.api.application.dto.query;

import dev.sojunx.ecommerce.api.domain.entities.product.Product;
import dev.sojunx.ecommerce.api.domain.enums.ProductCategory;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class ProductDetails {
    private UUID id;
    private String sku;
    private String name;
    private String description;
    private double price;
    private ProductCategory category;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDetails(Product product) {
        this.id = product.getId();
        this.sku = product.getSku();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }
}
