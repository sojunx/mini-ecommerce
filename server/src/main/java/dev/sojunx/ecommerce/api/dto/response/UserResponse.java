package dev.sojunx.ecommerce.api.dto.response;

import dev.sojunx.ecommerce.api.domain.enums.UserRole;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String email,
        String firstName,
        String lastName,
        UserRole role
) {
}
