package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;
import com.see1rg.simple_bancking.exception.InsufficientFundsException;
import com.see1rg.simple_bancking.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
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

        Account account = new Account(accountRequest.getName(), encodedPin, new BigDecimal(1_000_000));
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account deposit(Long id, DepositRequest depositRequest) {
        Account account = accountRepository.findById(id).orElseThrow();
        validateAccount(account, depositRequest.getPin(), depositRequest.getAmount());

        account.setBalance(account.getBalance().add(depositRequest.getAmount()));
        accountRepository.save(account);
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.isEmpty() ? Collections.emptyList() : accounts;
    }


    @Override
    public Account withdraw(Long id, WithdrawRequest withdrawRequest) {
        Account account = accountRepository.findById(id).orElseThrow();
        validateAccount(account, withdrawRequest.getPin(), withdrawRequest.getAmount());

        account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));
        accountRepository.save(account);
        return account;
    }

    @Override
    public void transfer(TransferRequest transferRequest) {
        Account accountFrom = accountRepository.findById(transferRequest.getFrom()).orElseThrow();
        validateAccount(accountFrom, transferRequest.getPin(), transferRequest.getAmount());

        Account accountTo = accountRepository.findById(transferRequest.getTo()).orElseThrow();

        accountFrom.setBalance(accountFrom.getBalance().subtract(transferRequest.getAmount()));
        accountTo.setBalance(accountTo.getBalance().add(transferRequest.getAmount()));

        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);
    }

    private void validateAccount(Account account, String inputPin, BigDecimal amount) {
        secureService.checkPin(inputPin, account.getPin());

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
    }

}
