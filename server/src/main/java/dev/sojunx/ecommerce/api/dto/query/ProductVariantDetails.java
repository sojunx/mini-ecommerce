package dev.sojunx.ecommerce.api.dto.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductVariantDetails {
    private String sku;
    private String size;
    private String color;
    private double price;
    private String imageUrl;
    private long stockQuantity;
}
