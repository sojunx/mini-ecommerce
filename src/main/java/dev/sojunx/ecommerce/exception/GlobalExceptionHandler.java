package dev.sojunx.ecommerce.exception;

import dev.sojunx.ecommerce.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex) {
        var res = ApiResponse.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(res);
    }

    @ExceptionHandler(InvalidCredentials.class)
    ResponseEntity<ApiResponse> handleInvalidCredentials(InvalidCredentials ex) {
        var res = ApiResponse.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(res);
    }

    @ExceptionHandler(InvalidException.class)
    ResponseEntity<ApiResponse> handleInvalidException(InvalidException ex) {
        var res = ApiResponse.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(res);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception ex) {
        var res = ApiResponse.error("Internal server error", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
}
