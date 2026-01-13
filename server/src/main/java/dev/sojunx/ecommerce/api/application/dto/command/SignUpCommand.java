package dev.sojunx.ecommerce.api.application.dto.command;

public record SignUpCommand(
        String email,
        String password,
        String fullName
) {
}
