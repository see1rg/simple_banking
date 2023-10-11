package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class DepositRequest {
    private Long id;
    @Positive
    private BigDecimal amount;

    public DepositRequest(Long id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public DepositRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
