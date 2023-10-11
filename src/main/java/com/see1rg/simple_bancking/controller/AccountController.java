package com.see1rg.simple_bancking.controller;

import com.see1rg.simple_bancking.dto.*;
import com.see1rg.simple_bancking.service.AccountService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@Validated
@RequestMapping("/account")
public class AccountController {
    private final Logger log = getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        log.info("Creating account {}", accountRequest.getName());
        return ResponseEntity.status(201).body(accountService.createAccount(accountRequest));
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountDTO> deposit(@Valid @RequestBody DepositRequest depositRequest) {
        log.info("Deposit {} id", depositRequest.getId());
        return ResponseEntity.ok(accountService.deposit(depositRequest));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        log.info("Withdraw {} id", withdrawRequest.getId());
        return ResponseEntity.ok(accountService.withdraw(withdrawRequest));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@Valid @RequestBody TransferRequest transferRequest) {
        log.info("Transfer {} to {}", transferRequest.getFrom(), transferRequest.getTo());
        accountService.transfer(transferRequest);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        log.info("Get all accounts");
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

}
