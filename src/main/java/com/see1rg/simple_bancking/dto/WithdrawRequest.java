package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class WithdrawRequest {

    String name;
    @Pattern(regexp = "\\d{4}", message = "the pin must consist of 4 digits")
    String pin;
    @Positive
    BigDecimal amount;

    public WithdrawRequest(String name, String pin, BigDecimal amount) {
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
