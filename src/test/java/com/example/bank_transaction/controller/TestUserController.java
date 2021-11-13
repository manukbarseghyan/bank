package com.example.bank_transaction.controller;


import com.example.bank_transaction.entity.User;
import com.example.bank_transaction.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class TestUserController {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Autowired
    private MockMvc mockMvc;

    private User user = null;

    @BeforeEach
    public void setup() {
        user = new User("Manuk", "Barseghyan", LocalDate.parse("1990-10-17"),
                "manukbarseghyan@mail.ru", "aaaa", "bbb");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    public void getUserByIdTest() throws Exception {
        when(userService.getUserById((int) user.getId())).thenReturn(user);
        mockMvc.perform(get("/user/1").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(user))).
                andExpect(status().isOk()).
                andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void createUserTest() throws Exception {
        when(userService.createUser(any())).thenReturn(user);
        mockMvc.perform(post("/user").
                        contentType(MediaType.APPLICATION_JSON).
                        content(asJsonString(user))).
                andExpect(status().isCreated());
        verify(userService, times(1)).createUser(any());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
