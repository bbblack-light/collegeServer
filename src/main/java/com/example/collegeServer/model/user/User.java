package com.example.collegeServer.model.user;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "m_User")
public class User {

    @Id
    private String userId;

    private String password = "";

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    @JsonIgnore
    private String address1;

    @JsonIgnore
    private String address2;

    @JsonIgnore
    private String country;

    @JsonIgnore
    private String postal;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isActive;

    public User() {
        new User(
                "new",
                "new",
                "new",
                "new",
                "",
                "",
                "",
                "",
                "",
                "",
                Role.STUDENT,
                null,
                0);
    }

    public User(String userId,
                String password,
                String firstName,
                String lastName,
                String email,
                String phone,
                String address1,
                String address2,
                String country,
                String postal,
                Role role,
                Gender gender,
                int bonus) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.country = country;
        this.postal = postal;
        this.role = role;
        this.gender = gender;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Enumerated(EnumType.STRING)
    private Gender gender;



    public String getFullName() {
        return this.firstName + this.lastName;
    }
}

