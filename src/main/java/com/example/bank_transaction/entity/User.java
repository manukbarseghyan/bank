package com.example.bank_transaction.entity;

import com.example.bank_transaction.enumaration.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@DynamicUpdate
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 20)
    private String lastname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "registration_date")
    @CreationTimestamp
    private Timestamp registrationDate;

    @Column(name = "role_id")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany()
    @JoinColumn(name = "bank_account_id")
    private Set<BankAccount> bankAccounts;

    @OneToMany()
    @JoinColumn(name = "transaction_id")
    private Set<Transaction> transactions;

    //public User(){this.role = Role.USER; }

    public User(String firstname, String lastname, LocalDate birthday, String email,
                String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = Role.USER;
    }
}