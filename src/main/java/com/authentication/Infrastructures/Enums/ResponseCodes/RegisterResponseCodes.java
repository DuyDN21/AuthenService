package com.authentication.Infrastructures.Enums.ResponseCodes;

public enum RegisterResponseCodes {

    CONFIRM_PW_NOT_MATCH("CONFIRM_PW_NOT_MATCH", "Register failed! Confirm password not match!"),
    USERNAME_EXISTED("USERNAME_EXISTED", "Register failed! Username existed!"),
    REGISTER_SUCCESS("REGISTER_SUCCESS", "Register successful!");

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
