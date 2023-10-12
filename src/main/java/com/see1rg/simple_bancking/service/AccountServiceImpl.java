package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.*;
import com.see1rg.simple_bancking.entity.Account;
import com.see1rg.simple_bancking.exception.AccountNotFoundException;
import com.see1rg.simple_bancking.exception.InsufficientFundsException;
import com.see1rg.simple_bancking.exception.SameAccountTransferException;
import com.see1rg.simple_bancking.mapper.AccountMapper;
import com.see1rg.simple_bancking.repository.AccountRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger log = getLogger(AccountServiceImpl.class);
    private final SecureService secureService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountAuditServiceImpl accountAuditService;

    public AccountServiceImpl(SecureService secureService, AccountRepository accountRepository, AccountMapper accountMapper, AccountAuditServiceImpl accountAuditService) {
        this.secureService = secureService;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.accountAuditService = accountAuditService;
    }

    @Override
    @Transactional
    public AccountDTO createAccount(AccountRequest accountRequest) {
        String encodedPin = secureService.encodePin(accountRequest.getPin());

        Account account = new Account(accountRequest.getName(), encodedPin, new BigDecimal(1_000_000));
        accountRepository.save(account);
        log.info("Created account {}", account.getName());
        accountAuditService.logAccountCreation(account.getName());

        return accountMapper.toAccountDTO(account);
    }

    @Override
    @Transactional
    public AccountDTO deposit(DepositRequest depositRequest) {
        Account account = accountRepository.findById(depositRequest.getId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setBalance(account.getBalance().add(depositRequest.getAmount()));
        accountRepository.save(account);

        log.info("Deposited {} to account {}", depositRequest.getAmount(), account.getName());
        accountAuditService.logAccountDeposit(account.getName(), depositRequest.getAmount());

        return accountMapper.toAccountDTO(account);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDTO> accountsDTO = accountMapper.toAccountsDTO(accounts);

        log.info("Get all accounts");
        return accounts.isEmpty() ? Collections.emptyList() : accountsDTO;
    }


    @Override
    @Transactional
    public AccountDTO withdraw(WithdrawRequest withdrawRequest) {
        Account account = accountRepository.findById(withdrawRequest.getId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (account.getBalance().compareTo(withdrawRequest.getAmount()) < 0) {
            throw new InsufficientFundsException("Not enough funds, you have " + account.getBalance());
        }

        account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));
        accountRepository.save(account);

        log.info("Withdraw {} from account {}", withdrawRequest.getAmount(), account.getName());
        accountAuditService.logAccountWithdraw(account.getName(), withdrawRequest.getAmount());

        return accountMapper.toAccountDTO(account);
    }

    @Override
    @Transactional
    public void transfer(TransferRequest transferRequest) {
        if (transferRequest.getFrom().equals(transferRequest.getTo())) {
            throw new SameAccountTransferException("The 'from' and 'to' accounts must be different.");
        }

        withdraw(accountMapper.toWithdrawRequest(transferRequest));
        deposit(accountMapper.toDepositRequest(transferRequest));

    }

}
