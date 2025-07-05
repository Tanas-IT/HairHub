package com.tan.java.hairhub.dto.response;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
