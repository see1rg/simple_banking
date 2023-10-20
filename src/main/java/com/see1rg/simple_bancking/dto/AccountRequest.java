package com.see1rg.simple_bancking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AccountRequest(@NotBlank @Size(min = 3, max = 35) String name,
                             @Pattern(regexp = "\\d{4}", message = "the pin must consist of 4 digits") String pin) {
}
