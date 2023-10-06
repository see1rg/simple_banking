package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountRequest {
    @NotBlank
    @Size(min = 3, max = 30)
    String name;
    @Pattern(regexp = "\\d{4}", message = "the pin must consist of 4 digits")
    String pin;

    public AccountRequest(String name, String pin) {
        this.name = name;
        this.pin = pin;
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
}
