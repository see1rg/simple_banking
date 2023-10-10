package com.see1rg.simple_bancking.exception;


import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger log = getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

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

    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        log.error("Account not found");
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(SameAccountTransferException.class)
    protected ResponseEntity<Object> handleSameAccountTransferException(SameAccountTransferException ex) {
        log.error("Same account transfer");
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
