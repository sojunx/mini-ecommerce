package dev.sojunx.ecommerce.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;
    private Pagination pagination;
    private ApiError error;

    private static ApiResponse build(boolean success, String message, Object data, Pagination pagination, ApiError error) {
        return ApiResponse.builder()
                .success(success)
                .message(message)
                .data(data)
                .pagination(pagination)
                .error(error)
                .build();

    }

    // With message, data, pagination
    public static ApiResponse success(String message, Object data, Pagination pagination) {
        return build(true, message, data, pagination, null);
    }

    // With message, data
    public static ApiResponse success(String message, Object data) {
        return success(message, data, null);
    }

    // With message
    public static ApiResponse success(String message) {
        return success(message, null);
    }

    public static ApiResponse error(String message, ApiError error) {
        return build(false, message, null, null, error);
    }
}
