package dev.sojunx.ecommerce.api.application.mapper;

import dev.sojunx.ecommerce.api.application.dto.command.SignUpCommand;
import dev.sojunx.ecommerce.api.application.dto.query.UserDetails;
import dev.sojunx.ecommerce.api.domain.entities.User;
import dev.sojunx.ecommerce.api.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder encoder;

    public UserDetails toDto(User user) {
        return UserDetails.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }

    public User toEntity(SignUpCommand command) {
        return User.builder()
                .email(command.email())
                .fullName(command.fullName())
                .passwordHash(encoder.encode(command.password()))
                .role(UserRole.USER)
                .build();
    }
}
