package com.authentication.Infrastructures.Enums.ResponseCodes;

public enum LoginResponseCodes {

    LOGIN_FAILED("LOGIN_FAILED", "Login failed! Wrong username or password!"),
    LOGIN_SUCCESS("LOGIN_SUCCESS", "Login successful"),
    USER_DISABLED("INACTIVE_USER", "Your account is disabled, contact the administrator!");

    private final String code;
    private final String desc;

    LoginResponseCodes(String code, String desc) {
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
