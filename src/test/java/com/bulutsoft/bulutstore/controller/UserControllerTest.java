package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("GET /api/users - should return 200 and user list")
    void getAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/users/{id} - should return 404 when not found")
    void getUserByIdNotFound() throws Exception {
        Mockito.when(userService.getUserById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/users/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // Diğer CRUD testleri de benzer şekilde eklenebilir.
}

