package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.dto.request.user.LoginRequest;
import dev.sojunx.ecommerce.dto.request.user.RegisterRequest;
import dev.sojunx.ecommerce.exception.InvalidCredentials;
import dev.sojunx.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final UserService service;
    private final PasswordEncoder encoder;

    public User login(LoginRequest req) {
        var user = service.getUserByEmail(req.getEmail());
        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new InvalidCredentials("Password do not match");

        return user;
    }

    public void register(RegisterRequest req) {
        if (!req.getPassword().equals(req.getConfirmPassword()))
            throw new InvalidCredentials("Passwords do not match");

        if (repository.existsByEmail(req.getEmail()))
            throw new InvalidCredentials("Email already exists");

        var user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setFullName(req.getFullName());

        repository.save(user);
    }
}
