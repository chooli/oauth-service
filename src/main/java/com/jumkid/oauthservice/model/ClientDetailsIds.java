package com.jumkid.oauthservice.model;

import java.util.List;

public class ClientDetailsIds {

    private final List<String> clientIds;

    public ClientDetailsIds(List<String> clientIds) {
        this.clientIds = clientIds;
    }

    public List<String> getClientIds() {
        return clientIds;
    }

}
