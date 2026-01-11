package dev.sojunx.ecommerce.api.dto.response;

import dev.sojunx.ecommerce.api.domain.enums.UserRole;

public record UserResponse(
        String email,
        String firstName,
        String lastName,
        UserRole role
) {
}
