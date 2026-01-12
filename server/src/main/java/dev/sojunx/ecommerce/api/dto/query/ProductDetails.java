package dev.sojunx.ecommerce.api.dto.query;

import dev.sojunx.ecommerce.api.domain.enums.ProductCategory;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ProductDetails {
    private UUID id;
    private String name;
    private String description;
    private double basePrice;
    private ProductCategory category;

    private String imageUrl;
    private List<ProductVariantDetails> variants;
}
