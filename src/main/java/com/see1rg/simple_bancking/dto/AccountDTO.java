package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class AccountDTO {
    private Long id;
    private String name;
    @Positive
    private BigDecimal balance;

    public AccountDTO(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public AccountDTO() {

    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
}
