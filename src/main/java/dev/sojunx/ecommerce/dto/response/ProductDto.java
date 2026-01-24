package dev.sojunx.ecommerce.dto.response;

import dev.sojunx.ecommerce.enums.ProductCategory;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private String description;
    private ProductCategory category;
    private Double price;
    private boolean isAvailable;
}
