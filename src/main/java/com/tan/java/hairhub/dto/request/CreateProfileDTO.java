package com.tan.java.hairhub.dto.request;

import com.tan.java.hairhub.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
