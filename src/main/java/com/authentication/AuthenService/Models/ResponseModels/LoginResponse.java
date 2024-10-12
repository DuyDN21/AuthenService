package com.authentication.AuthenService.Models.ResponseModels;

import com.authentication.AuthenService.Models.DTOs.UserLoginDTO;

public class LoginResponse extends Response {

    private final UserLoginDTO user;

    public LoginResponse(String code, String desc, UserLoginDTO user) {
        super(code, desc);
        this.user = user;
    }

    public UserLoginDTO getUser() {
        return user;
    }
}
