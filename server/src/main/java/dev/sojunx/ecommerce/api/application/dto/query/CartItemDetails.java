package dev.sojunx.ecommerce.api.application.dto.query;

import dev.sojunx.ecommerce.api.domain.entities.CartItem;
import lombok.Data;

import java.util.UUID;

@Data
public class CartItemDetails {
    private UUID id;
    private String sku;
    private String name;
    private double price;

    private int quantity;

    public CartItemDetails(CartItem cartItem) {
        var product = cartItem.getProduct();

        this.id = product.getId();
        this.sku = product.getSku();
        this.name = product.getName();
        this.price = product.getPrice();

        this.quantity = cartItem.getQuantity();
    }
}
