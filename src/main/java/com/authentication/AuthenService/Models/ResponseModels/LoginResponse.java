package com.authentication.AuthenService.Models.ResponseModels;

import com.authentication.AuthenService.Models.DatabaseModels.User;

public class LoginResponse extends Response {

    private final User user;

    public LoginResponse(String code, String desc, User user) {
        super(code, desc);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
