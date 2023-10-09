package com.see1rg.simple_bancking.controller;

import com.see1rg.simple_bancking.dto.*;
import com.see1rg.simple_bancking.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountRequest accountRequest,
                                                    BindingResult bindingResult) {
        validate(bindingResult);
        log.info("Creating account");
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @PostMapping("/deposit")
    public ResponseEntity<AccountDTO> deposit(@RequestBody @Valid DepositRequest depositRequest) {
        log.info("Deposit");
        return ResponseEntity.ok(accountService.deposit(depositRequest));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        log.info("Withdraw");
        return ResponseEntity.ok(accountService.withdraw(withdrawRequest));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest transferRequest) {
        log.info("Transfer");
        accountService.transfer(transferRequest);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        log.info("Get all accounts");
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    public void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Validation failed for account creation");
        }
    }
}
