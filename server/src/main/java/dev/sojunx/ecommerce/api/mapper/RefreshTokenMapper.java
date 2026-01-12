package dev.sojunx.ecommerce.api.mapper;

import dev.sojunx.ecommerce.api.domain.entities.user.RefreshToken;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenMapper {
    public RefreshToken toEntity(User user, String token) {
        return RefreshToken.builder()
                .user(user)
                .token(token)
                .revoked(false)
                .expired(false)
                .build();
    }
}
