package dev.sojunx.ecommerce.api.application.service.cart;

import dev.sojunx.ecommerce.api.application.dto.query.CartDetails;
import dev.sojunx.ecommerce.api.application.mapper.CartMapper;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.infrastructure.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository repo;
    private final CartMapper mapper;

    public CartDetails getCart(User user) {
        var result = repo.findByUser(user);
        if (result.isEmpty()) throw new RuntimeException("Cart not found");

        return mapper.toDto(result.get());
    }
}
