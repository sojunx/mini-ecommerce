package dev.sojunx.ecommerce.api.service.auth;

import dev.sojunx.ecommerce.api.domain.entities.user.RefreshToken;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.mapper.RefreshTokenMapper;
import dev.sojunx.ecommerce.api.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository repo;
    private final JwtService jwtService;
    private final RefreshTokenMapper mapper;

    public RefreshToken revoke(String token) {
        var result = repo.findByToken(token);
        if (result.isEmpty()) return null;

        var refreshToken = result.get();
        refreshToken.setRevoked(true);
        return repo.save(refreshToken);
    }

    public RefreshToken findByToken(String token) {
        var result = repo.findByToken(token);
        if (result.isEmpty())
            throw new RuntimeException("Token not found");

        return result.get();
    }

    public void save(User user, String token) {
        repo.save(mapper.toEntity(user, token));
    }

    public void revokeAll(User user) {
        var tokens = repo.findAllByUser(user);

        tokens.forEach(t -> t.setRevoked(true));
        repo.saveAll(tokens);
    }

    public boolean validate(String token) {
        var result = repo.findByToken(token);
        if (result.isEmpty()) return false;

        // Check revoke and expired
        var refreshToken = result.get();
        if (refreshToken.isRevoked() || refreshToken.isExpired()) return false;

        // Check expiration time
        var expiration = jwtService.extractExpiration(token);
        var isExpired = expiration.before(new Date()); // token is expired

        // Revoke token if expired
        if (isExpired) {
            refreshToken.setExpired(true);
            refreshToken.setRevoked(true);
            repo.save(refreshToken);
        }

        return !isExpired;
    }
}
