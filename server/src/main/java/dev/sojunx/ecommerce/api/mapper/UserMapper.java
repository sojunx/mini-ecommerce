package dev.sojunx.ecommerce.api.mapper;

import dev.sojunx.ecommerce.api.domain.entities.User;
import dev.sojunx.ecommerce.api.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }
}
