package com.authentication.Infrastructures.Enums.ResponseCodes;

public enum RegisterResponseCodes {

    LOGIN_FAILED("CONFIRM_PW_NOT_MATCH", "Register failed! Confirm password not match!"),
    LOGIN_SUCCESS("USERNAME_EXISTED", "Register failed! Username existed!"),
    USER_DISABLED("REGISTER_SUCCESS", "Register successful!");

    private final String code;
    private final String desc;

    RegisterResponseCodes(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
