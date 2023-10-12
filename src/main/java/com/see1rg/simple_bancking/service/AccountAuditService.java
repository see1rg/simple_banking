package com.see1rg.simple_bancking.service;

import java.math.BigDecimal;

public interface AccountAuditService {
    void logAccountCreation(String accountName);
    void logAccountWithdraw(String accountName, BigDecimal amount);
    void logAccountDeposit(String accountName, BigDecimal amount);


}
