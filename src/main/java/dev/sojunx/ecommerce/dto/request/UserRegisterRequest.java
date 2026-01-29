package dev.sojunx.ecommerce.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String name;
}
