package com.example.bank_transaction.repository;

import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.enumaration.Role;
import com.example.bank_transaction.repo.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TestUserRepo {


    @Autowired
    UserRepo userRepo;

    private User user;


    @BeforeEach
    public void setUp() {
        user = new User("Manuk", "Barseghyan", LocalDate.parse("1990-10-17"),
                "manukbarseghyan@mail.ru", "aaaa", "bbb");
    }


    @AfterEach
    public void truncate() {
        // userRepository.deleteAll();
        user = null;
    }


    @Test
    @Rollback(value = false)
    void registerUser() {
        userRepo.save(user);
        User fetchedUser = userRepo.findById(user.getId()).get();
        System.out.println(fetchedUser);
        assertEquals("Manuk", fetchedUser.getFirstname());
    }


    @Test
    public void getUserById() {
        userRepo.save(user);

        Optional<User> userById = userRepo.findById(this.user.getId());

        assertEquals(userById.get().getId(), user.getId());
        assertEquals(userById.get().getRole(), user.getRole());


    }
}
