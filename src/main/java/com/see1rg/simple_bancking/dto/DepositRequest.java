package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class DepositRequest {
    String name;
    @Pattern(regexp = "\\d{4}", message = "the pin must consist of 4 digits")
    int pin;
    @Positive
    BigDecimal amount;

    public DepositRequest(String name, int pin, BigDecimal amount) {
        this.name = name;
        this.pin = pin;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
