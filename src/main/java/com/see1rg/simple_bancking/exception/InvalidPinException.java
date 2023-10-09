package com.see1rg.simple_bancking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid pin")
public class InvalidPinException extends RuntimeException {
    public InvalidPinException(String message) {
        super("Invalid pin");
    }
}
