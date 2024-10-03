package com.authentication.AuthenService.Models.ResponseModels;

public class Response {
    private final String code;
    private final String description;

    public Response(String username, String password) {
        this.code = username;
        this.description = password;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
