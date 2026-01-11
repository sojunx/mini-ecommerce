package dev.sojunx.ecommerce.api.dto.response;

import dev.sojunx.ecommerce.api.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInResponse {
    private String token;
    private UserDto user;
}
