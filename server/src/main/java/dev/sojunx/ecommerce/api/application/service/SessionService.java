package dev.sojunx.ecommerce.api.application.service;

import dev.sojunx.ecommerce.api.application.mapper.SessionMapper;
import dev.sojunx.ecommerce.api.domain.entities.user.Session;
import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.infrastructure.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class SessionService {
    private final SessionRepository repo;
    private final JwtService jwtService;
    private final SessionMapper mapper;

    public Session revoke(String token) {
        var result = repo.findByToken(token);
        if (result.isEmpty()) return null;

        var session = result.get();
        session.setRevoked(true);
        return repo.save(session);
    }

    public Session findByToken(String token) {
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
        var Session = result.get();
        if (Session.isRevoked() || Session.isExpired()) return false;

        // Check expiration time
        var expiration = jwtService.extractExpiration(token);
        var isExpired = expiration.before(new Date()); // token is expired

        // Revoke token if expired
        if (isExpired) {
            Session.setExpired(true);
            Session.setRevoked(true);
            repo.save(Session);
        }

        return !isExpired;
    }
}
