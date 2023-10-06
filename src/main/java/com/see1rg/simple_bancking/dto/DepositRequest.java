package com.see1rg.simple_bancking.dto;

import java.math.BigDecimal;

public class DepositRequest {
    String name;
    int pin;
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
