package dev.sojunx.ecommerce.api.dto.command;

public record SignUpCommand(
        String email,
        String password,
        String firstName,
        String lastName
//        String confirmPassword
) {
}
