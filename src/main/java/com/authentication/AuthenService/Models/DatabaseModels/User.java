package com.authentication.AuthenService.Models.DatabaseModels;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column private String UserId;
    @Column private String Username;
    @Column private String Password;
    @Column private String FullName;
    @Column private Date Dob;
    @Column private String Email;
    @Column private boolean IsActive;

    public User() {
    }

    public User(String userId, String username, String password, String fullName, Date dob, String email, boolean isActive) {
        UserId = userId;
        Username = username;
        Password = password;
        FullName = fullName;
        Dob = dob;
        Email = email;
        IsActive = isActive;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
}
