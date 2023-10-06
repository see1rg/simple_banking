package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountRequest {
    @NotBlank
    @Size(min = 3, max = 25)
    String name;
    @Pattern(regexp = "\\d{4}", message = "the pin must consist of 4 digits")
    int pin;

    public AccountRequest(String name, int pin) {
        this.name = name;
        this.pin = pin;
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
}
