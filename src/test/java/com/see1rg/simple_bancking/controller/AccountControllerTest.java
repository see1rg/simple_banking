package com.see1rg.simple_bancking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.see1rg.simple_bancking.dto.AccountDTO;
import com.see1rg.simple_bancking.dto.AccountRequest;
import com.see1rg.simple_bancking.repository.AccountRepository;
import com.see1rg.simple_bancking.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createAccount() throws Exception {
        Long id = 1L;
        String name = "name";
        String pin = "1234";
        BigDecimal balance = new BigDecimal(1_000_000);
        AccountRequest accountRequest = new AccountRequest(name, pin);

        when(accountService.createAccount(any(AccountRequest.class)))
                .thenReturn(new AccountDTO(1L, "name", new BigDecimal(1_000_000)));
        mockMvc.perform(MockMvcRequestBuilders.post("/account/create")
                .content(asJsonString(accountRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.balance").value(balance))
                .andDo(print());
    }

    @Test
    void deposit() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void transfer() {
    }

    @Test
    void getAllAccounts() {
    }
    private String asJsonString(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}