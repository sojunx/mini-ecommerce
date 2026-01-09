package dev.sojunx.ecommerce.authorize.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private String message;
    private Object data;

    private static ApiResponse build(String message, Object data) {
        return ApiResponse.builder()
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse success(String message, Object data) {
        return build(message, data);
    }

    public static ApiResponse success(String message) { return build(message, null); }
}
