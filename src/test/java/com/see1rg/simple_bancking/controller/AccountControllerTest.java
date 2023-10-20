package com.see1rg.simple_bancking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.dto.DepositRequest;
import com.see1rg.simple_bancking.dto.TransferRequest;
import com.see1rg.simple_bancking.dto.WithdrawRequest;
import com.see1rg.simple_bancking.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private final AccountRequest accountRequest = new AccountRequest("Bob", "1234");
    private final DepositRequest depositRequest = new DepositRequest();
    private final WithdrawRequest withdrawRequest = new WithdrawRequest();
    private final TransferRequest transferRequest = new TransferRequest();


    @Test
    void shouldCreateAccount() throws Exception {

        mockMvc.perform(post("/account/create")
                        .content(asJsonString(accountRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldDepositMoney() throws Exception {

        mockMvc.perform(post("/account/deposit")
                        .content(asJsonString(depositRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldWithdrawMoney() throws Exception {

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