package com.jumkid.oauthservice.dao;

import com.jumkid.oauthservice.model.ClientDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDetailsRowMapper implements RowMapper<ClientDetails> {

    @Override
    public ClientDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ClientDetails.Builder()
                .clientId(rs.getString(ClientDetails.Fields.CLIENT_ID.columnName()))
                .resourceIds(rs.getString(ClientDetails.Fields.RESOURCE_IDS.columnName()))
                .webServerRedirectUri(rs.getString(ClientDetails.Fields.WEB_SERVER_REDIRECT_URI.columnName()))
                .authorizedGrantTypes(rs.getString(ClientDetails.Fields.AUTHORIZED_GRANT_TYPES.columnName()))
                .scope(rs.getString(ClientDetails.Fields.SCOPE.columnName()))
                .authorities(rs.getString(ClientDetails.Fields.AUTHORITIES.columnName()))
                .additionalInformation(rs.getString(ClientDetails.Fields.ADDITIONAL_INFORMATION.columnName()))
                .refreshTokenValidity(rs.getInt(ClientDetails.Fields.REFRESH_TOKEN_VALIDITY.columnName()))
                .accessTokenValidity(rs.getInt(ClientDetails.Fields.ACCESS_TOKEN_VALIDITY.columnName()))
                .autoapprove(rs.getBoolean(ClientDetails.Fields.AUTO_APPROVE.columnName()))
                .build();
    }
}
