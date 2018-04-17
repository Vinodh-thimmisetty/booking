package com.vinodh.booking.api.utils;

public enum UserRole {
    SUPER_ADMIN(100), ADMIN(50), USER(10), INVALID(-1);

    private final int factor;

    UserRole(int factor) {
        this.factor = factor;
    }

    public int getFactor() {
        return factor;
    }
}
