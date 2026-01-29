package dev.sojunx.ecommerce.mapper;

import dev.sojunx.ecommerce.domain.entity.User;
import dev.sojunx.ecommerce.dto.response.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
