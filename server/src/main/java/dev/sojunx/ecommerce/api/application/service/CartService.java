package dev.sojunx.ecommerce.api.application.service;

import dev.sojunx.ecommerce.api.application.dto.command.AddToCartCommand;
import dev.sojunx.ecommerce.api.application.dto.query.CartDetails;
import dev.sojunx.ecommerce.api.application.mapper.CartMapper;
import dev.sojunx.ecommerce.api.domain.entities.CartItem;
import dev.sojunx.ecommerce.api.domain.entities.User;
import dev.sojunx.ecommerce.api.infrastructure.repository.CartRepository;
import dev.sojunx.ecommerce.api.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repo;
    private final CartMapper mapper;
    private final ProductRepository productRepo;

    public CartDetails findByUser(User user) {
        var result = repo.findByUser(user);
        if (result.isEmpty()) throw new RuntimeException("Cart not found");

        return mapper.toDto(result.get());
    }

    @Transactional
    public void addToCart(User user, AddToCartCommand command) {
        var cart = repo.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));

        var product = productRepo.findById(command.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        var existItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(command.getProductId()))
                .findFirst();

        if (existItem.isPresent())
            existItem.get().setQuantity(existItem.get().getQuantity() + command.getQuantity());
        else {
            var item = new CartItem();
            item.setProduct(product);
            item.setQuantity(command.getQuantity());
            item.setCart(cart);

            cart.getItems().add(item);
        }

        repo.save(cart);
    }
}
