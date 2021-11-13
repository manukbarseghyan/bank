package com.example.bank_transaction.service;

import com.example.bank_transaction.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    Transaction getById(long id);

    void delete(Transaction transaction);

    Boolean createTransaction(long loggedId, Transaction transaction);

    Boolean cancelTransaction(long loggedId, Transaction transaction);

    //Method for Admin
    List<Transaction> getAllPendingTransactions(long loggedId);

    Boolean acceptTransaction(long loggedId, Transaction transaction);
}
