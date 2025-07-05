package com.tan.java.hairhub.dto.response;

import com.tan.java.hairhub.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {

    private int profileId;

    private LocalDate birthDate;

    private String bio;

    private int yearOfExperience;

    private String fullName;

    private String address;

    private String gender;

    private int userId;

}
