package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.dto.request.UserLoginRequest;
import dev.sojunx.ecommerce.dto.request.UserRegisterRequest;
import dev.sojunx.ecommerce.exception.NotFoundException;
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
        var user = getUserByEmail(request.getEmail());

        user.setActive(true);
        return repository.save(user);
    }

    public void logout(String email) {
        var user = getUserByEmail(email);

        user.setActive(false);
        repository.save(user);
    }

    public User getUserById(UUID id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new NotFoundException("User not found");

        return result.get();
    }

    public User getUserByEmail(String email) {
        var result = repository.findByEmail(email);
        if (result.isEmpty())
            throw new NotFoundException("User not found");

        return result.get();
    }
}
