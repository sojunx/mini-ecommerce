package dev.sojunx.ecommerce.api.dto.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDetails {
    private ProductVariantDetails product;
    private int quantity;
}
