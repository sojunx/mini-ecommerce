package dev.sojunx.ecommerce.api.mapper;

import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.domain.enums.UserRole;
import dev.sojunx.ecommerce.api.dto.command.SignUpCommand;
import dev.sojunx.ecommerce.api.dto.query.UserDetails;
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
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }

    public User toEntity(SignUpCommand command) {
        return User.builder()
                .email(command.email())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .passwordHash(encoder.encode(command.password()))
                .role(UserRole.USER)
                .build();
    }
}
