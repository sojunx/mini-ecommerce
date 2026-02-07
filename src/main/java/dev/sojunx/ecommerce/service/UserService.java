package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.exception.NotFoundException;
import dev.sojunx.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    
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
