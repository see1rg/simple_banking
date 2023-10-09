package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;
import com.see1rg.simple_bancking.exception.AccountNotFoundException;
import com.see1rg.simple_bancking.exception.InsufficientFundsException;
import com.see1rg.simple_bancking.exception.InvalidAmountException;
import com.see1rg.simple_bancking.mapper.AccountMapper;
import com.see1rg.simple_bancking.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {
    private final SecureService secureService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(SecureService secureService, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.secureService = secureService;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public Account createAccount(AccountRequest accountRequest) {
        String encodedPin = secureService.encodePin(accountRequest.getPin());

        Account account = new Account(accountRequest.getName(), encodedPin, new BigDecimal(1_000_000));
        accountRepository.save(account);
        return account;
    }

    @Override
    @Transactional
    public Account deposit(DepositRequest depositRequest) {
        Account account = accountRepository.findById(depositRequest.getId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

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
    @Transactional
    public Account withdraw( WithdrawRequest withdrawRequest) {
        Account account = accountRepository.findById(withdrawRequest.getId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        validateAccount(account, withdrawRequest.getPin(), withdrawRequest.getAmount());

        account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));
        accountRepository.save(account);
        return account;
    }

    @Override
    @Transactional
    public void transfer(TransferRequest transferRequest) {
        if (Objects.equals(transferRequest.getFrom(), transferRequest.getTo())) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        withdraw(accountMapper.toWithdrawRequest(transferRequest));
        deposit(accountMapper.toDepositRequest(transferRequest));

    }

    private void validateAccount(Account account, String inputPin, BigDecimal amount) {
        secureService.checkPin(inputPin, account.getPin());

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Invalid amount");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Not enough funds, you have " + account.getBalance());
        }
    }

}
