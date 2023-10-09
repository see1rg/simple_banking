package com.see1rg.simple_bancking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The 'from' and 'to' accounts must be different.")
public class SameAccountTransferException extends RuntimeException {
    public SameAccountTransferException(String message) {
    super(message);
    }
}
