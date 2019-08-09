package com.jumkid.oauthservice.dao;

import com.jumkid.oauthservice.model.ClientDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class OauthClientDetailsDao {

    static Logger logger = LoggerFactory.getLogger(OauthClientDetailsDao.class);

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OauthClientDetailsDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /**
     * Add new client details entity into database
     *
     * @param clientDetails
     * @return
     */
    public int add(ClientDetails clientDetails) {
        String updateQuery = "INSERT INTO oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)" +
                " VALUES(:clientId, :resourceIds, :clientSecret, :scope, :authorizedGrantTypes, :webServerRedirectUri, :authorities, :accessTokenValidity, :refreshTokenValidity, :additionalInformation, :autoapprove)";

        Map<String, Object> params = new HashMap<>();
        params.put(ClientDetails.Fields.CLIENT_ID.value(), clientDetails.getClientId());
        params.put(ClientDetails.Fields.RESOURCE_IDS.value(), clientDetails.getResourceIds());
        params.put(ClientDetails.Fields.CLIENT_SECRET.value(), clientDetails.getClientSecret());
        params.put(ClientDetails.Fields.SCOPE.value(), clientDetails.getScope());
        params.put(ClientDetails.Fields.AUTHORIZED_GRANT_TYPES.value(), clientDetails.getAuthorizedGrantTypes());
        params.put(ClientDetails.Fields.WEB_SERVER_REDIRECT_URI.value(), clientDetails.getWebServerUedirectUri());
        params.put(ClientDetails.Fields.AUTHORITIES.value(), clientDetails.getAuthorities());
        params.put(ClientDetails.Fields.ACCESS_TOKEN_VALIDITY.value(), clientDetails.getAccessTokenValidity());
        params.put(ClientDetails.Fields.REFRESH_TOKEN_VALIDITY.value(), clientDetails.getRefreshTokenValidity());
        params.put(ClientDetails.Fields.ADDITIONALINFORMATION.value(), clientDetails.getAdditionalInformation());
        params.put(ClientDetails.Fields.AUTOAPPROVE.value(), clientDetails.isAutoapprove());

        int row = namedParameterJdbcTemplate.update(updateQuery, params);
        logger.info("inserted new client details {}", row);
        return row;
    }

    /**
     * Update single field of client details record regarding the given client id
     *
     * @param clientId
     * @param fieldName
     * @param value
     * @return
     */
    public int updateField(String clientId, String fieldName, Object value) {
        String queryForUpdate = "UPDATE oauth_client_details SET ? = ? WHERE client_id = ?";

        int row = jdbcTemplate.update(queryForUpdate, new Object[] {fieldName, value, clientId});
        logger.debug("updated client details {}", row);
        return row;
    }

    /**
     * Fetch a list of client ids
     *
     * @return
     */
    public List<String> getAllClientIds() {
        String query = "SELECT client_id FROM oauth_client_details";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);
        logger.debug("query results {}", results.size());
        return results.stream().map( map -> (String)map.values().iterator().next() ).collect(Collectors.toList());
    }

    /**
     * Fetch a client details by given clientId
     *
     * @param clientId
     * @return
     */
    public ClientDetails get(String clientId) {
        String query = "SELECT client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove FROM oauth_client_details " +
                "WHERE client_id = ?";

        ClientDetails clientDetails = jdbcTemplate.queryForObject(query, new Object[] {clientId}, ClientDetails.class);
        logger.debug("found client details for client id {}", clientId);
        return clientDetails;
    }

}
