package com.tan.java.hairhub.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tan.java.hairhub.dto.request.CreateUserDTO;
import com.tan.java.hairhub.dto.response.CreateUserResponse;
import com.tan.java.hairhub.services.interfaces.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    private CreateUserDTO userRequest;

    private CreateUserResponse userResponse;

    private LocalDate dob;

    @BeforeEach
    public void initData() {
        dob = LocalDate.of(1990, 1, 1);

        userRequest = CreateUserDTO.builder()
                .email("john@gmail.com")
                .fullName("David John")
                .password("123456")
                .birthDate(dob)
                .roleId(1)
                .build();

        userResponse = CreateUserResponse.builder()
                .email("john@gmail.com")
                .fullName("David John")
                .password("123456")
                .roleId(1)
                .birthDate(dob)
                .build();
    }

    @Test
    //
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(userRequest);

        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);
        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("statusCode").value(201))
                .andExpect(MockMvcResultMatchers.jsonPath("data.email").value("john@gmail.com"));

        // THEN
        log.info("Test Init Success");
    }

    @Test
    //
    void createUser_phoneInvalid_fail() throws Exception {
        Object result = null;
        // GIVEN
        userRequest.setPhoneNumber("012345678910111213141516171819");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(userRequest);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("statusCode").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Create user failed"));

        // THEN
    }
}
