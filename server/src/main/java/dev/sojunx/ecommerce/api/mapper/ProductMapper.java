package dev.sojunx.ecommerce.api.mapper;

import dev.sojunx.ecommerce.api.domain.entities.product.Product;
import dev.sojunx.ecommerce.api.domain.entities.product.ProductVariant;
import dev.sojunx.ecommerce.api.dto.query.ProductDetails;
import dev.sojunx.ecommerce.api.dto.query.ProductVariantDetails;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDetails toDto(Product product) {
        return ProductDetails.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .basePrice(product.getBasePrice())
                .category(product.getCategory())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public ProductVariantDetails toDto(ProductVariant variant) {
        return ProductVariantDetails.builder()
                .sku(variant.getSku())
                .size(variant.getSize())
                .color(variant.getColor())
                .price(variant.getPrice())
                .imageUrl(variant.getImageUrl())
                .build();
    }
}
