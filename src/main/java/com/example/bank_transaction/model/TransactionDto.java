package com.example.bank_transaction.model;


import com.example.bank_transaction.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TransactionDto {
    private Timestamp createdAt;
    private String transactionType;
    private String transactionStatus;
    private BigDecimal transactionSum;
    private User user;
}
