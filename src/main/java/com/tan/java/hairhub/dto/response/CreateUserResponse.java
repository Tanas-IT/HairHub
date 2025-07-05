package com.tan.java.hairhub.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private String email;

    private String password;

    private String avatarURL;

    private String phoneNumber;

    private boolean isActive;

    private LocalDate birthDate;

    private String bio;

    private int yearOfExperience;

    private String fullName;

    private String address;

    private String gender;

    private int roleId;

    private String accessToken;
    private String refreshToken;
}
