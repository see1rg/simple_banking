package com.see1rg.simple_bancking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPinException extends RuntimeException {
    public InvalidPinException(String message) {
        super(message);
    }

    public InvalidPinException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPinException(Throwable cause) {
        super(cause);
    }
}
