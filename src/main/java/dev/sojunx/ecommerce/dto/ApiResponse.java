package dev.sojunx.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
    private Object data;

    private static ApiResponse build(String message, boolean success, Object data) {
        return ApiResponse.builder()
                .message(message)
                .success(success)
                .data(data)
                .build();
    }

    public static ApiResponse success(String message, Object data) {
        return build(message, true, data);
    }

    public static ApiResponse success(String message) {
        return success(message, null);
    }

    public static ApiResponse error(String message) {
        return build(message, false, null);
    }
}
