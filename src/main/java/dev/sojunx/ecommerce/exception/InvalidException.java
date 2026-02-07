package dev.sojunx.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidException extends RuntimeException{
    private final HttpStatus status = HttpStatus.BAD_REQUEST;

    public InvalidException(String message) {
        super(message);
    }
}
