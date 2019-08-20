package com.jumkid.oauthservice.model;

import java.util.List;

public class Usernames {

    List<String> usernames;

    public Usernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public List<String> getUsernames() {
        return usernames;
    }

}

