package dev.sojunx.ecommerce.api.dto.query;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartDetails {
    private List<CartItemDetails> cartItems;
}
