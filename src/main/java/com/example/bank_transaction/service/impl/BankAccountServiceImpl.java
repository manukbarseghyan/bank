package com.example.bank_transaction.service.impl;

import com.example.bank_transaction.entity.BankAccount;
import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.repo.BankAccountRepo;
import com.example.bank_transaction.repo.UserRepo;
import com.example.bank_transaction.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepo bankAccountRepo;
    private final UserRepo userRepo;
    public BankAccountServiceImpl(BankAccountRepo bankAccountRepo, UserRepo userRepo) {
        this.bankAccountRepo = bankAccountRepo;
        this.userRepo = userRepo;
    }



    @Override
    public void save(BankAccount bankAccount) {

        BankAccount bankAccount1;

    }
@Override
    public BankAccount createBankAccount(long id) {
        User user = userRepo.getById(id);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setUser(user);
        user.getBankAccounts().add(bankAccount);
        bankAccountRepo.save(bankAccount);
        userRepo.save(user);
        return bankAccount;
    }


}
