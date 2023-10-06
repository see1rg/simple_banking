package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;
import com.see1rg.simple_bancking.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final SecureService secureService;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(SecureService secureService, AccountRepository accountRepository) {
        this.secureService = secureService;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(AccountRequest accountRequest) {
        String encodedPin = secureService.encodePin(accountRequest.getPin());
        Account account = new Account(accountRequest.getName(), encodedPin, new BigDecimal(1000));
        accountRepository.save(account);
        return account;
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
