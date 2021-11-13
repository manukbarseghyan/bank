package com.example.bank_transaction.service.impl;

import com.example.bank_transaction.entity.Transaction;
import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.enumaration.Role;
import com.example.bank_transaction.model.BankAccountDto;
import com.example.bank_transaction.model.UserDto;
import com.example.bank_transaction.repo.UserRepo;
import com.example.bank_transaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User createUser(User user) {
        String encodedString = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        user.setPassword(encodedString);
        user.setRole(Role.USER);
        return userRepo.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User getUserById(long userId) {
        return userRepo.findById(userId).get();
    }

    @Override
    public User checkLogin(User user) {
        User logUser = getUserByUsername(user.getUsername());
        if (logUser != null) {
            String userPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
            if (userPassword.equals(logUser.getPassword())) {
                // success
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Set<Transaction> getUserHistory(long userId) {
        User user = getUserById(userId);
        if (user.getTransactions() == null) {
            return null;
        } else {
            Set<Transaction> set = user.getTransactions();
            for (Transaction tr : set){
                tr.setUser(null);
            }
            return set;
        }
    }

    @Override
    public User changeRoleOfUser(long loggedId, User toBeChangedUser) {
        User loggedUser = userRepo.findById(loggedId).get();
        if (loggedUser.getRole().equals(Role.ADMIN)) {
            User userToChangeRole = userRepo.findByUsername(toBeChangedUser.getUsername());
            if (userToChangeRole != null) {
                userToChangeRole.setRole(toBeChangedUser.getRole());
                userRepo.save(userToChangeRole);
                return userToChangeRole;
            } else {
                return null;
            }
        }
        else{
            return null;
        }
    }

}
