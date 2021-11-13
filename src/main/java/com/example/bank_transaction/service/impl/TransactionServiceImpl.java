package com.example.bank_transaction.service.impl;

import com.example.bank_transaction.entity.BankAccount;
import com.example.bank_transaction.entity.Transaction;
import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.enumaration.Role;
import com.example.bank_transaction.enumaration.TransactionStatus;
import com.example.bank_transaction.enumaration.TransactionType;
import com.example.bank_transaction.repo.BankAccountRepo;
import com.example.bank_transaction.repo.TransactionRepo;
import com.example.bank_transaction.repo.UserRepo;
import com.example.bank_transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    private final UserRepo userRepo;

    private final BankAccountRepo bankAccountRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo, UserRepo userRepo, BankAccountRepo bankAccountRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.bankAccountRepo = bankAccountRepo;
    }


    @Override
    public Transaction getById(long id) {
        return transactionRepo.getById(id);
    }

    @Override
    public void delete(Transaction transaction) {
        if (transaction.getTransactionStatus().equals(TransactionStatus.PENDING))
            transactionRepo.delete(transaction);
    }

    @Override
    public Boolean createTransaction(long loggedId, Transaction transaction) {
        User user = userRepo.findById(loggedId).get();
        if (transaction.getSum() != 0) {
            transaction.setUser(user);
            transactionRepo.save(transaction);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean cancelTransaction(long loggedId, Transaction transaction) {
        Transaction transaction1 = transactionRepo.findById(transaction.getId()).get();
        User user = userRepo.findById(loggedId).get();
        if (transaction1.getTransactionStatus() != null && transaction1.getTransactionStatus().equals(TransactionStatus.PENDING)) {
            if (transaction1.getUser().getId() == user.getId()) {
                transactionRepo.delete(transaction1);
            }
            return true;
        } else {
            return false;
        }
    }

    //Method for Admin
    @Override
    public List<Transaction> getAllPendingTransactions(long loggedId) {
        User user = userRepo.getById(loggedId);
        if (!user.getRole().equals(Role.USER)) {
            List<Transaction> transactionsByStatus = transactionRepo.findAllByTransactionStatus(TransactionStatus.PENDING);
            for (Transaction tr : transactionsByStatus) {
                tr.setUser(null);
            }
            return transactionsByStatus;
        } else {
            return null;
        }
    }

    @Override
    public Boolean acceptTransaction(long loggedId, Transaction transaction) {
        BankAccount bankAccount;
        User user = userRepo.findById(loggedId).get();
        if (user.getRole().equals(Role.ADMIN)) {

            Transaction transaction1 = transactionRepo.findById(transaction.getId()).get();
            if (transaction1 != null) {
                bankAccount = (BankAccount) transaction1.getUser().getBankAccounts();
            } else {
                return false;
            }
            if (bankAccount != null) {
                transaction1.setTransactionStatus(TransactionStatus.ACCEPTED);
                if (transaction1.getTransactionType().equals(TransactionType.DEPOSIT)) {
                    bankAccount.setBalance(bankAccount.getBalance() + transaction1.getSum());
                } else if (transaction1.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
                    bankAccount.setBalance(bankAccount.getBalance() - transaction1.getSum());
                }
                transactionRepo.save(transaction1);
                bankAccountRepo.save(bankAccount);
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

}
