package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Account createAccount(AccountRequest accountRequest) {

        return null;
    }

    @Override
    public Account deposit(Long id, DepositRequest depositRequest) {
        return null;
    }

    @Override
    public Optional<Account> getAllAccounts() {
        return Optional.empty();
    }

    @Override
    public Account withdraw(Long id, WithdrawRequest withdrawRequest) {
        return null;
    }

    @Override
    public String transfer(TransferRequest transferRequest) {
        return null;
    }
}
