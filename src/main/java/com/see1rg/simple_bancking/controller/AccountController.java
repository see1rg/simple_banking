package com.see1rg.simple_bancking.controller;

import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;
import com.see1rg.simple_bancking.service.AccountService;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final Logger log = getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        log.info("Creating account");
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody DepositRequest depositRequest) {
        log.info("Deposit");
        return ResponseEntity.ok(accountService.deposit(id, depositRequest));
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody WithdrawRequest withdrawRequest) {
        log.info("Withdraw");
        return ResponseEntity.ok(accountService.withdraw(id, withdrawRequest));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest transferRequest) {
        log.info("Transfer");
        accountService.transfer(transferRequest);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/all")
    public ResponseEntity<Optional<Account>> getAllAccounts() {
        log.info("Get all accounts");
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
