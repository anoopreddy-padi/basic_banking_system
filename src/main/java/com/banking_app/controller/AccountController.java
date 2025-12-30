package com.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking_app.dto.AccountDto;
import com.banking_app.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping()
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> DepositMoneyById(@PathVariable Long id, @RequestBody Map<String,Double> request){
        double money = request.get("money");
        return new ResponseEntity<>(accountService.depositMoneyById(id, money),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdrawMoneyById(@PathVariable Long id, @RequestBody Map<String,Double> request){
        double money = request.get("money");
        return new ResponseEntity<>(accountService.withdrawMoneyById(id, money),HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

}
