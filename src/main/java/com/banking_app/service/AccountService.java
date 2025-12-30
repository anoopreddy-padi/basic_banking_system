package com.banking_app.service;

import com.banking_app.dto.AccountDto;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto depositMoneyById(Long id, double money);

    AccountDto withdrawMoneyById(Long id,double money);
    
}