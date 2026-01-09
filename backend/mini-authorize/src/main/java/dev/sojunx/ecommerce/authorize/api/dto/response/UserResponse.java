package dev.sojunx.ecommerce.authorize.api.dto.response;

import dev.sojunx.ecommerce.authorize.api.model.UserRole;

public record UserResponse(
        String email,
        String firstName,
        String lastName,
        UserRole role
) {
}
