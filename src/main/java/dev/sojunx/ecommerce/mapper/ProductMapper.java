package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.dto.response.ProductDto;
import dev.sojunx.ecommerce.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        var isAvailable = product.getStock() > 0;

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .isAvailable(isAvailable)
                .build();
    }
}
