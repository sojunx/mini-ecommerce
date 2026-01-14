package dev.sojunx.ecommerce.api.application.mapper;

import dev.sojunx.ecommerce.api.application.dto.query.ProductDetails;
import dev.sojunx.ecommerce.api.domain.entities.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDetails toDto(Product product) {
        return new ProductDetails(product);
    }
}
