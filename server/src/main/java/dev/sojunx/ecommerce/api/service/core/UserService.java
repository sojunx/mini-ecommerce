package dev.sojunx.ecommerce.api.service.core;

import dev.sojunx.ecommerce.api.domain.entities.User;
import dev.sojunx.ecommerce.api.domain.enums.UserRole;
import dev.sojunx.ecommerce.api.dto.request.SignUpRequest;
import dev.sojunx.ecommerce.api.dto.response.UserResponse;
import dev.sojunx.ecommerce.api.mapper.UserMapper;
import dev.sojunx.ecommerce.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    public void createUser(SignUpRequest request) {
        var user = new User();
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));
        user.setRole(UserRole.USER);

        repo.save(user);
    }

    public UserResponse getUserByEmail(String email) {
        var result = repo.findByEmail(email);
        if (result.isEmpty())
            throw new UsernameNotFoundException("User not found with email: " + email);

        var user = result.get();
        return new UserResponse(user.getEmail(), user.getFirstName(), user.getLastName(), user.getRole());
    }
}
