package com.tan.java.hairhub.dto.request;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileDTO {

    private LocalDate birthDate;

    private String bio;

    private int yearOfExperience;

    private String fullName;

    private String address;

    private String gender;

    private int userId;
}
