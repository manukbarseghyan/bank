package com.example.bank_transaction.model;

import com.example.bank_transaction.entity.User;
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
public class BankAccountDto {

    private double balance;
    private Timestamp createdAt;
    private User user;
    private Set<TransactionDto> transactions;

}
