package com.jumkid.oauthservice.utils;

public enum UserRoles {

    USER("USER"),
    ADMIN("ADMIN");

    private String value;

    UserRoles(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}