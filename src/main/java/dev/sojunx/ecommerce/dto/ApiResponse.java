package dev.sojunx.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private String message;
    private boolean success;
    private Object data;
    private String errorMsg;

    private static ApiResponse build(String message, boolean success, Object data, String errorMsg) {
        return ApiResponse.builder()
                .message(message)
                .success(success)
                .data(data)
                .errorMsg(errorMsg)
                .build();
    }

    public static ApiResponse success(String message, Object data) {
        return build(message, true, data, null);
    }

    public static ApiResponse success(String message) {
        return success(message, null);
    }

    public static ApiResponse error(String message, String errorMsg) {
        return build(message, false, null, errorMsg);
    }

    public static ApiResponse error(String message) {
        return error(message, null);
    }
}
