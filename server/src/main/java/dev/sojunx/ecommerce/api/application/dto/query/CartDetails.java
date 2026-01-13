package dev.sojunx.ecommerce.api.application.dto.query;

import lombok.Data;

import java.util.List;

@Data
public class CartDetails {
    private List<CartItemDetails> cartItems;
}
