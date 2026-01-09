package dev.sojunx.ecommerce.authorize.api.service;

import dev.sojunx.ecommerce.authorize.api.model.Token;
import dev.sojunx.ecommerce.authorize.api.model.User;
import dev.sojunx.ecommerce.authorize.api.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {
    private final TokenRepository repo;
    private final JwtService jwtService;

    public void revoke(String token) {
        var result = repo.findByToken(token);
        if (result.isEmpty()) return;

        var refreshToken = result.get();
        refreshToken.setRevoked(true);
        repo.save(refreshToken);
    }

    public void save(User user, String token) {
        var refreshToken = new Token();
        refreshToken.setToken(token);
        refreshToken.setRevoked(false);
        refreshToken.setExpired(false);
        refreshToken.setUser(user);

        repo.save(refreshToken);
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
