package com.see1rg.simple_bancking.exception;

public class InvalidPinException extends RuntimeException {
    public InvalidPinException(String message) {
        super("Invalid pin");
    }
}
