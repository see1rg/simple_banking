package com.see1rg.simple_bancking.mapper;

import com.see1rg.simple_bancking.dto.AccountDTO;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {

    AccountDTO toAccountDTO(Account account);

    @Mapping(target = "id", source = "from")
    WithdrawRequest toWithdrawRequest(TransferRequest transferRequest);

    @Mapping(target = "id", source = "to")
    DepositRequest toDepositRequest(TransferRequest transferRequest);

    List<AccountDTO> toAccountsDTO(List<Account> accounts);
}
