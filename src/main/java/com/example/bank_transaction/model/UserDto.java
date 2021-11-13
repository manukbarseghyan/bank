package com.example.bank_transaction.model;

import com.example.bank_transaction.enumaration.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private String password;
    private Timestamp createdAt;
    private Role role;
    private Set<TransactionDto> transactions;
    private Set<BankAccountDto> bankAccounts;
}
