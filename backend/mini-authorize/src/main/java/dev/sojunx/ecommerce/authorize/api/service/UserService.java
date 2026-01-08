package dev.sojunx.ecommerce.authorize.api.service;

import dev.sojunx.ecommerce.authorize.api.dto.SignUpRequest;
import dev.sojunx.ecommerce.authorize.api.dto.UserResponse;
import dev.sojunx.ecommerce.authorize.api.model.User;
import dev.sojunx.ecommerce.authorize.api.model.UserRole;
import dev.sojunx.ecommerce.authorize.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserResponse createUser(SignUpRequest request) {
        var user = new User();
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setRole(UserRole.ADMIN);

        var savedUser = repo.save(user);
        return new UserResponse(savedUser.getEmail(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getRole());
    }
}
