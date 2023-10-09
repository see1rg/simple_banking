package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(AccountRequest accountRequest);

    Account deposit(DepositRequest depositRequest);

    List<Account> getAllAccounts();

    Account withdraw(WithdrawRequest withdrawRequest);

    void transfer(TransferRequest transferRequest);
}
