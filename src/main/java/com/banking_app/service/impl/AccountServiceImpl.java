package com.banking_app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.banking_app.dto.AccountDto;
import com.banking_app.entity.Account;
import com.banking_app.mapper.AccountMapper;
import com.banking_app.repository.AccountRepository;
import com.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto){
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
        System.out.println(account);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositMoneyById(Long id, double money){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not Found"));
        account.setBalance(account.getBalance()+money);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawMoneyById(Long id, double money){
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not Found"));
        account.setBalance(account.getBalance()-money);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
}
