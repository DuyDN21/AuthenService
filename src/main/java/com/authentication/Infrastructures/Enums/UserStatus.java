package com.authentication.Infrastructures.Enums;

public enum UserStatus {
    Inactive(0),
    Active(1);

    private final int value;

    UserStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
