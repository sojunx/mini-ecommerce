package dev.sojunx.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidCredentials extends RuntimeException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public InvalidCredentials(String message) {
        super(message);
    }
}
