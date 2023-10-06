package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransferRequest {
    Long from;
    Long to;
    @Positive
    BigDecimal amount;
    @Pattern(regexp = "\\d{4}", message = "the pin must consist of 4 digits")
    String pin;

    public TransferRequest(Long from, Long to, BigDecimal amount, String pin) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.pin = pin;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
