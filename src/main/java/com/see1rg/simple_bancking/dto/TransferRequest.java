package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransferRequest {

    private Long from;
    private Long to;
    @Positive
    private BigDecimal amount;
    private String pin;

    public TransferRequest(Long from, Long to, BigDecimal amount, String pin) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.pin = pin;
    }

    public TransferRequest() {

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
