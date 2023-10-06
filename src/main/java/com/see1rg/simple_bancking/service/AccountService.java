package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;
import com.see1rg.simple_bancking.repository.AccountRepository;

import java.util.Optional;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account createAccount(AccountRequest accountRequest) {
    }

    public Account deposit(Long id, DepositRequest depositRequest) {
    }

    public Optional<Account> getAllAccounts() {
    }

    public Account withdraw(Long id, WithdrawRequest withdrawRequest) {
    }

    public String transfer(TransferRequest transferRequest) {
    }
}
