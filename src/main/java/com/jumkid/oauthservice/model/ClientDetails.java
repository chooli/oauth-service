package com.jumkid.oauthservice.model;

import java.util.Map;

public class ClientDetails {

    public enum Fields {
        CLIENT_ID("clientId", "client_id"), RESOURCE_IDS("resourceIds", "resource_ids"),
        CLIENT_SECRET("clientSecret", "client_secret"), SCOPE("scope", "scope"),
        AUTHORIZED_GRANT_TYPES("authorizedGrantTypes", "authorized_grant_types"),
        WEB_SERVER_REDIRECT_URI("webServerRedirectUri", "web_server_redirect_uri"),
        AUTHORITIES("authorities", "authorities"), ACCESS_TOKEN_VALIDITY("accessTokenValidity", "access_token_validity"),
        REFRESH_TOKEN_VALIDITY("refreshTokenValidity", "refresh_token_validity"),
        ADDITIONAL_INFORMATION("additionalInformation", "additional_information"), AUTO_APPROVE("autoapprove", "autoapprove");

        private String value;
        private String columnName;
        private static Map<String, String> columnMap;

        Fields(String value, String columnName) { this.value = value; this.columnName = columnName; }

        public String value() { return this.value; }
        public String columnName() { return this.columnName; }

        public static String getColumnName(String value) {
            if(columnMap == null) {
                initColumnMap();
            }
            return columnMap.get(value);
        }

        private static void initColumnMap() {
            columnMap = Map.ofEntries(
                    Map.entry(CLIENT_ID.value, CLIENT_ID.columnName),
                    Map.entry(RESOURCE_IDS.value, RESOURCE_IDS.columnName),
                    Map.entry(CLIENT_SECRET.value, CLIENT_SECRET.columnName),
                    Map.entry(SCOPE.value, SCOPE.columnName),
                    Map.entry(AUTHORIZED_GRANT_TYPES.value, AUTHORIZED_GRANT_TYPES.columnName),
                    Map.entry(WEB_SERVER_REDIRECT_URI.value, WEB_SERVER_REDIRECT_URI.columnName),
                    Map.entry(AUTHORITIES.value, AUTHORITIES.columnName),
                    Map.entry(ACCESS_TOKEN_VALIDITY.value, ACCESS_TOKEN_VALIDITY.columnName),
                    Map.entry(REFRESH_TOKEN_VALIDITY.value, REFRESH_TOKEN_VALIDITY.columnName),
                    Map.entry(AUTO_APPROVE.value, AUTO_APPROVE.columnName),
                    Map.entry(ADDITIONAL_INFORMATION.value, ADDITIONAL_INFORMATION.columnName)
                    );
        }

    }

    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private boolean autoapprove;

    private ClientDetails(Builder builder) {
        clientId = builder.clientId;
        resourceIds = builder.resourceIds;
        clientSecret = builder.clientSecret;
        scope = builder.scope;
        authorizedGrantTypes = builder.authorizedGrantTypes;
        webServerRedirectUri = builder.webServerRedirectUri;
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

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
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
        private String webServerRedirectUri;
        private String authorities;
        private Integer accessTokenValidity;
        private Integer refreshTokenValidity;
        private String additionalInformation;
        private boolean autoapprove;

        public ClientDetails build() { return new ClientDetails(this); }

        public Builder clientId(String val) { clientId = val; return this; }
        public Builder resourceIds(String val) { resourceIds = val; return this; }
        public Builder clientSecret(String val) { clientSecret = val; return this; }
        public Builder scope(String val) { scope = val; return this; }
        public Builder authorizedGrantTypes(String val) { authorizedGrantTypes = val; return this; }
        public Builder webServerRedirectUri(String val) { webServerRedirectUri = val; return this; }
        public Builder authorities(String val) { authorities = val; return this; }
        public Builder accessTokenValidity(Integer val) { accessTokenValidity = val; return this; }
        public Builder refreshTokenValidity(Integer val) { refreshTokenValidity = val; return this; }
        public Builder additionalInformation(String val) { additionalInformation = val; return this; }
        public Builder autoapprove(boolean val) { autoapprove = val; return this; }

    }

}
