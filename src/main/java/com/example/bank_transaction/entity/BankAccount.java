package com.example.bank_transaction.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@DynamicUpdate
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "account_balance")
    private double balance;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public BankAccount() {
        this.accountNumber = generatorAccountNumber();
        this.balance = 0;
    }

    private static long number = 1234567800000000L;

    private String generatorAccountNumber() {
        long newNumber = number++;

        return String.valueOf(newNumber);
    }
}
