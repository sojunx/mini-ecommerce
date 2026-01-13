package dev.sojunx.ecommerce.api.application.mapper;

import dev.sojunx.ecommerce.api.domain.entities.Session;
import dev.sojunx.ecommerce.api.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {
    public Session toEntity(User user, String token) {
        return Session.builder()
                .user(user)
                .token(token)
                .revoked(false)
                .expired(false)
                .build();
    }
}
