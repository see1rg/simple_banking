package com.see1rg.simple_bancking.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecureService {

    private final PasswordEncoder encoder;

    public SecureService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encodePin(String pin) {
        return encoder.encode(pin);
    }

    public boolean checkPin(String pin, String encodedPin) {
        return encoder.matches(pin, encodedPin);
    }

}