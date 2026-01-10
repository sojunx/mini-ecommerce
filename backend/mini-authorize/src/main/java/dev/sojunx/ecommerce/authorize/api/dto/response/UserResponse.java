package dev.sojunx.ecommerce.authorize.api.dto.response;

import dev.sojunx.ecommerce.authorize.api.domain.enums.UserRole;

public record UserResponse(
        String email,
        String firstName,
        String lastName,
        UserRole role
) {
}
