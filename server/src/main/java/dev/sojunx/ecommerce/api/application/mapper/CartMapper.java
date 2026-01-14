package dev.sojunx.ecommerce.api.application.mapper;

import dev.sojunx.ecommerce.api.application.dto.query.CartDetails;
import dev.sojunx.ecommerce.api.application.dto.query.CartItemDetails;
import dev.sojunx.ecommerce.api.domain.entities.cart.Cart;
import dev.sojunx.ecommerce.api.domain.entities.cart.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public CartDetails toDto(Cart cart) {
        var items = cart.getItems();

        var details = new CartDetails();
        details.setCartItems(items.stream().map(this::toDto).toList());

        return details;
    }

    public CartItemDetails toDto(CartItem item) {
        return new CartItemDetails(item);
    }
}
