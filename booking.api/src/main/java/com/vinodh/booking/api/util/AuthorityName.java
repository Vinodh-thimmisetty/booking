package com.vinodh.booking.api.util;

public enum AuthorityName {

    ROLE_SUPER_ADMIN(100), ROLE_ADMIN(50), ROLE_USER(10), UNSUPPORTED(-1);

    private final int value;

    AuthorityName(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}