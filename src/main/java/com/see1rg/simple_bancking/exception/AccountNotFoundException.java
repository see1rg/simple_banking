package com.see1rg.simple_bancking.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super("Account not found");
    }
}
