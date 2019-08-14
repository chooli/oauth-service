package com.jumkid.oauthservice.dao;

import com.jumkid.oauthservice.model.ClientDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDetailsRowMapper implements RowMapper<ClientDetails> {

    @Override
    public ClientDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ClientDetails.Builder()
                .clientId(rs.getString("client_id"))
                .clientSecret(rs.getString("client_secret"))
                .resourceIds(rs.getString("resource_ids"))
                .webServerRedirectUri(rs.getString("web_server_redirect_uri"))
                .authorizedGrantTypes(rs.getString("authorized_grant_types"))
                .scope(rs.getString("scope"))
                .authorities(rs.getString("authorities"))
                .additionalInformation(rs.getString("additional_information"))
                .refreshTokenValidity(rs.getInt("refresh_token_validity"))
                .accessTokenValidity(rs.getInt("access_token_validity"))
                .autoapprove(rs.getBoolean("autoapprove"))
                .build();
    }
}
