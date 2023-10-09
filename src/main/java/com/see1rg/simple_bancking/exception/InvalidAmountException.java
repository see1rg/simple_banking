package com.see1rg.simple_bancking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid amount")
public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(String message) {
        super("Invalid amount");
    }
}
