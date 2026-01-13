package dev.sojunx.ecommerce.api.service.core;

import dev.sojunx.ecommerce.api.domain.entities.user.User;
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
    private final ProductVariantRepository productVariantRepo;

    public CartDetails findByUser(User user) {
        var result = repo.findByUser(user);
        if (result.isEmpty()) throw new RuntimeException("Cart not found");

        var cart = result.get();
        return mapper.toDto(cart);
    }

    @Transactional
    public void addProduct(User user, String sku) {
        
    }
}
