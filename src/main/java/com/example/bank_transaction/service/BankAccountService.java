package com.example.bank_transaction.service;

import com.example.bank_transaction.entity.BankAccount;
import org.springframework.stereotype.Service;

@Service
public interface BankAccountService {

    void save(BankAccount bankAccount);

    BankAccount createBankAccount(long id);
}
