package com.example.bank_transaction.service;


import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.enumaration.Role;
import com.example.bank_transaction.repo.UserRepo;
import com.example.bank_transaction.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepo userRepo;

    private User user = null;

    @BeforeEach
    public void setUp() {
        String pass = "bbb";
        String savePass = Base64.getEncoder().encodeToString(pass.getBytes());
        user = new User("Manuk","Barseghyan", LocalDate.parse("1990-10-17"),
                "manukbarseghyan@mail.ru","aaaa",savePass);
    }


    @Test
    public void getUserByIdTest() {
        when(userRepo.findById(0L)).thenReturn(Optional.ofNullable(user));
        assertThat(userService.getUserById(user.getId())).isEqualTo(user);
    }

    @Test
    void registerUser() {
        when(userRepo.save(any())).thenReturn(user);
        userService.createUser(user);
        //verify(userRepo, times(1)).save(user);

    }
}