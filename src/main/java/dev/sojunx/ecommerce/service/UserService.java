package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.dto.request.UserLoginRequest;
import dev.sojunx.ecommerce.dto.request.UserLogoutRequest;
import dev.sojunx.ecommerce.dto.request.UserRegisterRequest;
import dev.sojunx.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User createUser(UserRegisterRequest request) {
        var user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());

        return repository.save(user);
    }

    public User authenticate(UserLoginRequest request) {
        var result = repository.findByEmail(request.getEmail());
        if (result.isEmpty())
            throw new RuntimeException("Invalid credentials");

        var user = result.get();
        user.setActive(true);

        return repository.save(user);
    }

    public void logout(UserLogoutRequest request) {
        var result = repository.findByEmail(request.getEmail());
        if (result.isEmpty())
            throw new RuntimeException("Invalid credentials");

        var user = result.get();
        user.setActive(false);
        repository.save(user);
    }

    public User getUserById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new RuntimeException("User not found with id: " + id);

        return result.get();
    }
}
