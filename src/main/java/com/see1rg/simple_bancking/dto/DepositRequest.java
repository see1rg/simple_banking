package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class DepositRequest {
    Long id;
    String name;
    @Pattern(regexp = "\\d{4}", message = "the pin must consist of 4 digits")
    String pin;
    @Positive
    BigDecimal amount;

    public DepositRequest(Long id, String name, String pin, BigDecimal amount) {
        this.id = id;
        this.name = name;
        this.pin = pin;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
