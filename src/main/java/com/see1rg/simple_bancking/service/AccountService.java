package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.repository.AccountRepository;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
