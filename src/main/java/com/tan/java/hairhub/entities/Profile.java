package com.tan.java.hairhub.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileId")
    private int profileId;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column(name = "bio")
    private String bio;

    @Column(name = "yearOfExperience")
    private int yearOfExperience;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private String gender;

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    private User user;

    public Profile() {}

    public Profile(
            LocalDate birthDate,
            String bio,
            int yearOfExperience,
            String fullName,
            String address,
            String gender,
            User user) {
        this.birthDate = birthDate;
        this.bio = bio;
        this.yearOfExperience = yearOfExperience;
        this.fullName = fullName;
        this.address = address;
        this.gender = gender;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
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
}
