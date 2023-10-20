package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountDTO(Long id, String name, @Positive BigDecimal balance) {

}
