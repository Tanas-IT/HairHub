package com.tan.java.hairhub.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.tan.java.hairhub.dto.request.CreateUserDTO;
import com.tan.java.hairhub.entities.Role;
import com.tan.java.hairhub.entities.User;
import com.tan.java.hairhub.repositories.UserRepository;
import com.tan.java.hairhub.services.interfaces.UserService;

@SpringBootTest
// @TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private CreateUserDTO userRequest;

    private CreateUserDTO userResponse;

    private LocalDate dob;

    private User user;
    private Optional<User> userCreateResponse;

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

        userResponse = CreateUserDTO.builder()
                .email("john@gmail.com")
                .fullName("David John")
                .password("123456")
                .birthDate(dob)
                .roleId(1)
                .build();

        user = User.builder()
                .userId(1)
                .email("john@gmail.com")
                .password("123456")
                .phoneNumber("012345")
                .role(new Role("ADMIN"))
                .build();

        userCreateResponse = Optional.of(User.builder()
                .userId(1)
                .email("john@gmail.com")
                .password("123456")
                .phoneNumber("012345")
                .role(new Role("ADMIN"))
                .build());
    }

    //    @Test
    //    void createUser_userExisted_failed() {
    //        // SETUP AUTHENTICATION
    //        var authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    //        var auth = new UsernamePasswordAuthenticationToken("testUser", null, authorities);
    //        SecurityContextHolder.getContext().setAuthentication(auth);
    //        // GIVEN
    //        when(userRepository.findByEmail(anyString())).thenReturn(userCreateResponse);
    //
    //        // When
    //        var exception = assertThrows(Exception.class, () -> userService.createUser(userRequest));
    //
    //        // Then
    //
    //        Assertions.assertThat(userCreateResponse.get().getEmail()).isEqualTo("john@gmail.com");
    //        Assertions.assertThat(exception.getMessage()).isEqualTo("User is already existed");
    //    }

    @Test
    @WithMockUser(
            username = "john",
            roles = {"ADMIN"})
    void getMyInfo_valid_success() throws Exception {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        var response = userService.getUserInfo();

        Assertions.assertThat(response.getEmail()).isEqualTo("john@gmail.com");
        Assertions.assertThat(response.getPhoneNumber()).isEqualTo("012345");
    }

    @Test
    @WithMockUser(
            username = "john",
            roles = {"ADMIN"})
    void getMyInfo_userNotFound_error() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(Exception.class, () -> userService.getUserInfo());

        Assertions.assertThat(exception.getMessage()).isEqualTo("User does not exist");
    }
}
