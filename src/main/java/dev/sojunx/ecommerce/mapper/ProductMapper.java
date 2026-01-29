package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.domain.entity.Product;
import dev.sojunx.ecommerce.dto.response.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .build();
    }
}
