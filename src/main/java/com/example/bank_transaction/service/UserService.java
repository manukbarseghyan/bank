package com.example.bank_transaction.service;


import com.example.bank_transaction.entity.Transaction;
import com.example.bank_transaction.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    User createUser(User user);

    User getUserByUsername(String username);

    User getUserById(long userId);

    User checkLogin(User user);

    Set<Transaction> getUserHistory(long userId);

    User changeRoleOfUser(long loggedId, User toBeChangedUser);
}
