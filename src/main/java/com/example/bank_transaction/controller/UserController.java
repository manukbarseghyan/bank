package com.example.bank_transaction.controller;

import com.example.bank_transaction.entity.Transaction;
import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.service.BankAccountService;
import com.example.bank_transaction.service.TransactionService;
import com.example.bank_transaction.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private BankAccountService bankAccountService;

    private TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User registeredUser = userService.createUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {

        User logUser = userService.checkLogin(user);
        if (logUser != null) {
            return new ResponseEntity<>(logUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/userHistory/{id}")
    public ResponseEntity<Set<Transaction>> userHistoryPost(@PathVariable Integer id) {
        Set<Transaction> set = userService.getUserHistory(id);
        if (set == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>(set, HttpStatus.OK);
        }
    }

    /**
     * @param user .
     * @return .
     */
    @PutMapping("/edit_users/{id}")
    public ResponseEntity<String> userEditPost(@PathVariable Integer id, @RequestBody User user) {
        User toBeChanged = userService.changeRoleOfUser(id, user);
        if (toBeChanged == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        } else {
            return new ResponseEntity<>("text ", HttpStatus.OK);
        }
    }
}