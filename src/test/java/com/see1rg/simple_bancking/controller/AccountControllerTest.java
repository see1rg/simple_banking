package com.see1rg.simple_bancking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.see1rg.simple_bancking.dto.*;
import com.see1rg.simple_bancking.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountServiceImpl accountService;
    private final AccountDTO accountDTO = new AccountDTO();
    private final AccountRequest accountRequest = new AccountRequest();
    private final DepositRequest depositRequest = new DepositRequest();
    private final WithdrawRequest withdrawRequest = new WithdrawRequest();
    private final TransferRequest transferRequest = new TransferRequest();


    @Test
    void shouldCreateAccount() throws Exception {

        accountRequest.setName("Bob");
        accountRequest.setPin("1234");

        when(accountService.createAccount(any(AccountRequest.class)))
                .thenReturn(accountDTO);

        mockMvc.perform(post("/account/create")
                        .content(asJsonString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldDepositMoney() throws Exception {

        when(accountService.deposit(any(DepositRequest.class)))
                .thenReturn(accountDTO);

        mockMvc.perform(post("/account/deposit")
                        .content(asJsonString(depositRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldWithdrawMoney() throws Exception {

        when(accountService.withdraw(any(WithdrawRequest.class)))
                .thenReturn(accountDTO);

        mockMvc.perform(post("/account/withdraw")
                        .content(asJsonString(withdrawRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldTransferMoney() throws Exception {

        mockMvc.perform(post("/account/transfer")
                        .content(asJsonString(transferRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldGetAllAccounts() throws Exception {

        mockMvc.perform(get("/account/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private String asJsonString(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}