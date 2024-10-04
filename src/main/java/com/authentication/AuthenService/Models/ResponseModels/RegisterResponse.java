package com.authentication.AuthenService.Models.ResponseModels;

import com.authentication.AuthenService.Models.DatabaseModels.User;

public class RegisterResponse extends Response{
    private final User user;

    public RegisterResponse(String code, String desc, User user) {
        super(code, desc);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
