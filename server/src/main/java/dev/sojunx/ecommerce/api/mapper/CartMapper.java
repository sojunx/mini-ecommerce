package dev.sojunx.ecommerce.api.mapper;

import dev.sojunx.ecommerce.api.domain.entities.cart.Cart;
import dev.sojunx.ecommerce.api.domain.entities.cart.CartItem;
import dev.sojunx.ecommerce.api.dto.query.CartDetails;
import dev.sojunx.ecommerce.api.dto.query.CartItemDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {
    private final ProductMapper productMapper;

    public CartDetails toDto(Cart cart) {
        var items = cart.getItems();

        return CartDetails.builder()
                .cartItems(items.stream().map(this::toDto).toList())
                .build();
    }

    public CartItemDetails toDto(CartItem item) {
        return CartItemDetails.builder()
                .product(productMapper.toDto(item.getVariant()))
                .quantity(item.getQuantity())
                .build();
    }
}
