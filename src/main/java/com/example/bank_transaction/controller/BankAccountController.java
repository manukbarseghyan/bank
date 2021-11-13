package com.example.bank_transaction.controller;

import com.example.bank_transaction.entity.BankAccount;
import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank_account")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @PostMapping("/create_bank_account/{id}")
    public ResponseEntity<BankAccount> createBankAccount(@PathVariable Long id, @RequestBody User user) {
        BankAccount bankAccount = bankAccountService.createBankAccount(id);
        if (bankAccount != null) {
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}