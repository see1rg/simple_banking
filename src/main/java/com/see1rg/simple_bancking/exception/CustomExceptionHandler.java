package com.see1rg.simple_bancking.exception;


import jakarta.validation.ValidationException;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = getLogger(CustomExceptionHandler.class);
    @ExceptionHandler(InvalidPinException.class)
    protected ResponseEntity<Object> handleInvalidPinException(InvalidPinException ex) {
        log.error("Invalid pin");
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    protected ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException ex) {
        log.error("Not enough funds");
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidAmountException.class)
    protected ResponseEntity<Object> handleInvalidAmountException(InvalidAmountException ex) {
        log.error("Invalid amount");
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        log.error("Account not found");
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        log.error("Validation failed");
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(SameAccountTransferException.class)
    protected ResponseEntity<Object> handleSameAccountTransferException(SameAccountTransferException ex) {
        log.error("Same account transfer");
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
