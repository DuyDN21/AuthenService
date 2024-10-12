package com.authentication.AuthenService.Models.DTOs;

import java.util.Date;

public class UserLoginDTO {
    private String Username;
    private String FullName;
    private Date Dob;
    private String Email;
    private boolean IsActive;
    private String RefreshToken;
    private String AccessToken;

    public UserLoginDTO(
            String username,
            String fullName,
            Date dob,
            String email,
            boolean isActive,
            String refreshToken,
            String accessToken) {
        Username = username;
        FullName = fullName;
        Dob = dob;
        Email = email;
        IsActive = isActive;
        RefreshToken = refreshToken;
        AccessToken = accessToken;
    }

    public String getUsername() {
        return Username;
    }

    public String getFullName() {
        return FullName;
    }

    public Date getDob() {
        return Dob;
    }

    public String getEmail() {
        return Email;
    }

    public boolean isActive() {
        return IsActive;
    }

    public String getRefreshToken() {
        return RefreshToken;
    }

    public String getAccessToken() {
        return AccessToken;
    }
}
