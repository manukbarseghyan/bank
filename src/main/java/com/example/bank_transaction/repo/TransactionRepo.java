package com.example.bank_transaction.repo;

import com.example.bank_transaction.entity.Transaction;
import com.example.bank_transaction.enumaration.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByTransactionStatus(TransactionStatus transactionStatus);
}
