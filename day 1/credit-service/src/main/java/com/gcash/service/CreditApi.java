package com.gcash.service;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // magiging API na siya
public class CreditApi {
    private final CreditService creditService;

    public CreditApi(CreditService creditService) {
        this.creditService = creditService;
    }

    // create a new account
    public Account createAccount(Double initialBalance) {
        return creditService.createAccount(initialBalance);
    }

    // retrieve all accounts
    public List<Account> getAllAccounts() {
        return creditService.getAllAccounts();
    }
}
