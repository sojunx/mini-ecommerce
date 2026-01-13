package dev.sojunx.ecommerce.api.service.core;

import dev.sojunx.ecommerce.api.domain.entities.cart.CartItem;
import dev.sojunx.ecommerce.api.domain.entities.product.ProductVariant;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.dto.command.AddToCartCommand;
import dev.sojunx.ecommerce.api.dto.query.CartDetails;
import dev.sojunx.ecommerce.api.mapper.CartMapper;
import dev.sojunx.ecommerce.api.repository.CartRepository;
import dev.sojunx.ecommerce.api.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repo;
    private final CartMapper mapper;
    private final ProductVariantRepository variantRepo;

    public CartDetails findByUser(User user) {
        var result = repo.findByUser(user);
        if (result.isEmpty()) throw new RuntimeException("Cart not found");

        var cart = result.get();
        return mapper.toDto(cart);
    }

    @Transactional
    public void addProduct(User user, AddToCartCommand command) {
        var result = repo.findByUser(user);
        if (result.isEmpty()) throw new RuntimeException("Cart not found");

        var cart = result.get();

        var result1 = variantRepo.findBySku(command.getSku());
        if (result1.isEmpty()) throw new RuntimeException("Variant not found");

        var variant = result1.get();
        if (variant.getStockQuantity() < command.getQuantity())
            throw new RuntimeException("Insufficient stock");

        var cartItem = new CartItem();
        cartItem.setQuantity(command.getQuantity());
        cartItem.setVariant(variant);
        cartItem.setCart(cart);

        cart.getItems().add(cartItem);
        repo.save(cart);
    }
}
