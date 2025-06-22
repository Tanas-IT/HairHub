package com.tan.java.hairhub.dto.response;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public class UserDTO {

    private int userId;

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

    private String roleName;

    public UserDTO() {}

    public UserDTO(
            int userId,
            String email,
            String password,
            String avatarURL,
            String phoneNumber,
            boolean isActive,
            LocalDate birthDate,
            String bio,
            int yearOfExperience,
            String fullName,
            String address,
            String gender,
            String roleName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.avatarURL = avatarURL;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.birthDate = birthDate;
        this.bio = bio;
        this.yearOfExperience = yearOfExperience;
        this.fullName = fullName;
        this.address = address;
        this.gender = gender;
        this.roleName = roleName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(int yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
