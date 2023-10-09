package com.see1rg.simple_bancking.service;

import com.see1rg.simple_bancking.dto.*;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountRequest accountRequest);

    AccountDTO deposit(DepositRequest depositRequest);

    List<AccountDTO> getAllAccounts();

    AccountDTO withdraw(WithdrawRequest withdrawRequest);

    void transfer(TransferRequest transferRequest);
}
