package com.jumkid.oauthservice.model;

public class ClientDetails {

    public enum Fields {
        CLIENT_ID("clientId"), RESOURCE_IDS("resourceIds"), CLIENT_SECRET("clientSecret"),
        SCOPE("scope"), AUTHORIZED_GRANT_TYPES("authorizedGrantTypes"), WEB_SERVER_REDIRECT_URI("webServerUedirectUri"),
        AUTHORITIES("authorities"),ACCESS_TOKEN_VALIDITY("accessTokenValidity"),REFRESH_TOKEN_VALIDITY("refreshTokenValidity"),
        ADDITIONALINFORMATION("additionalInformation"), AUTOAPPROVE("autoapprove");

        private String value;

        Fields(String value) { this.value = value; }

        public String value() { return this.value; }

    }

    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerUedirectUri;
    private String authorities;
    private int accessTokenValidity;
    private int refreshTokenValidity;
    private String additionalInformation;
    private boolean autoapprove;

    private ClientDetails(Builder builder) {
        clientId = builder.clientId;
        resourceIds = builder.resourceIds;
        clientSecret = builder.clientSecret;
        scope = builder.scope;
        authorizedGrantTypes = builder.authorizedGrantTypes;
        webServerUedirectUri = builder.webServerUedirectUri;
        authorities = builder.authorities;
        accessTokenValidity = builder.accessTokenValidity;
        refreshTokenValidity = builder.refreshTokenValidity;
        additionalInformation = builder.additionalInformation;
        autoapprove = builder.autoapprove;
    }

    public String getClientId() {
        return clientId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public String getWebServerUedirectUri() {
        return webServerUedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public boolean isAutoapprove() {
        return autoapprove;
    }

    public static class Builder {

        private String clientId;
        private String resourceIds;
        private String clientSecret;
        private String scope;
        private String authorizedGrantTypes;
        private String webServerUedirectUri;
        private String authorities;
        private int accessTokenValidity;
        private int refreshTokenValidity;
        private String additionalInformation;
        private boolean autoapprove;

        public ClientDetails build() { return new ClientDetails(this); }

        public Builder clientId(String val) { clientId = val; return this; }
        public Builder resourceIds(String val) { resourceIds = val; return this; }
        public Builder clientSecret(String val) { clientSecret = val; return this; }
        public Builder scope(String val) { scope = val; return this; }
        public Builder authorizedGrantTypes(String val) { authorizedGrantTypes = val; return this; }
        public Builder webServerUedirectUri(String val) { webServerUedirectUri = val; return this; }
        public Builder authorities(String val) { authorities = val; return this; }
        public Builder accessTokenValidity(int val) { accessTokenValidity = val; return this; }
        public Builder refreshTokenValidity(int val) { refreshTokenValidity = val; return this; }
        public Builder additionalInformation(String val) { additionalInformation = val; return this; }
        public Builder autoapprove(boolean val) { autoapprove = val; return this; }

    }

}
