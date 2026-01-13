package dev.sojunx.ecommerce.api.application.dto.query;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserDetails {
    private UUID id;
    private String email;
    private String fullName;
}
