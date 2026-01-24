package dev.sojunx.ecommerce.service;

import dev.sojunx.ecommerce.dto.request.LoginRequest;
import dev.sojunx.ecommerce.dto.request.RegisterRequest;
import dev.sojunx.ecommerce.dto.response.UserDto;
import dev.sojunx.ecommerce.entity.User;
import dev.sojunx.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserDto getUserByEmail(String email) {
        var result = repository.findByEmail(email);
        if (result.isEmpty())
            throw new RuntimeException("User not found with email: " + email);

        var user = result.get();
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public UserDto authenticate(LoginRequest request) {
        var result = repository.findByEmail(request.getEmail());
        if (result.isEmpty())
            throw new RuntimeException("User not found with email: " + request.getEmail());

        var user = result.get();
        if (!encoder.matches(user.getPassword(), request.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public UserDto createUser(RegisterRequest request) {
        var user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        var saved = repository.save(user);
        return UserDto.builder()
                .id(saved.getId())
                .email(saved.getEmail())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .build();
    }
}
