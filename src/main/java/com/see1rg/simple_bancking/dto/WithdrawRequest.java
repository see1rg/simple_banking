package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class WithdrawRequest {
    private Long id;
    private String pin;
    @Positive
    private BigDecimal amount;

    public WithdrawRequest(Long id, String pin, BigDecimal amount) {
        this.id = id;
        this.pin = pin;
        this.amount = amount;
    }

    public WithdrawRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
