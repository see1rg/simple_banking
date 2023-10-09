package com.see1rg.simple_bancking.mapper;

import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AccountMapper {

//    AccountRequest toAccountRequest(Account account);
//
//    Account toAccount(AccountRequest accountRequest);

    @Mapping(target = "id", source = "from")
    @Mapping(target = "name", ignore = true)
    WithdrawRequest toWithdrawRequest(TransferRequest transferRequest);

    @Mapping(target = "id", source = "to")
    @Mapping(target = "name", ignore = true)
    DepositRequest toDepositRequest(TransferRequest transferRequest);

}
