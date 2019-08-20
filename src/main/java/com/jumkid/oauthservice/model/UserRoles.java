package com.jumkid.oauthservice.model;

import java.util.List;

public class UserRoles {

    List<String> userRoles;

    public UserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }
}
