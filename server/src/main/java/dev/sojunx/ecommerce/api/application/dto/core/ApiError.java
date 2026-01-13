package dev.sojunx.ecommerce.api.application.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String code;
    private Object details;
}
