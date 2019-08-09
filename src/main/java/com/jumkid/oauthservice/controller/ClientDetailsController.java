package com.jumkid.oauthservice.controller;

import com.jumkid.oauthservice.dao.OauthClientDetailsDao;
import com.jumkid.oauthservice.model.ClientDetails;
import com.jumkid.oauthservice.model.ClientDetailsIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/clientdetails")
public class ClientDetailsController {

    private final OauthClientDetailsDao oauthClientDetailsDao;

    @Autowired
    public ClientDetailsController(OauthClientDetailsDao oauthClientDetailsDao) {
        this.oauthClientDetailsDao = oauthClientDetailsDao;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public ClientDetails Add(@RequestBody Map<String, Object> payload){
        ClientDetails newEntity = new ClientDetails.Builder()
                .clientId((String)payload.get(ClientDetails.Fields.CLIENT_ID.value()))
                .resourceIds((String)payload.get(ClientDetails.Fields.RESOURCE_IDS.value()))
                .clientSecret((String)payload.get(ClientDetails.Fields.CLIENT_SECRET.value()))
                .scope((String)payload.get(ClientDetails.Fields.SCOPE.value()))
                .authorizedGrantTypes((String)payload.get(ClientDetails.Fields.AUTHORIZED_GRANT_TYPES.value()))
                .webServerUedirectUri((String)payload.get(ClientDetails.Fields.WEB_SERVER_REDIRECT_URI.value()))
                .authorities((String)payload.get(ClientDetails.Fields.AUTHORITIES.value()))
                .accessTokenValidity((Integer) payload.get(ClientDetails.Fields.ACCESS_TOKEN_VALIDITY.value()))
                .refreshTokenValidity((Integer) payload.get(ClientDetails.Fields.REFRESH_TOKEN_VALIDITY.value()))
                .additionalInformation((String)payload.get(ClientDetails.Fields.ADDITIONALINFORMATION.value()))
                .autoapprove((Boolean)payload.get(ClientDetails.Fields.AUTOAPPROVE.value()))
                .build();

        oauthClientDetailsDao.add(newEntity);

        return newEntity;
    }

    @RequestMapping(value = "/allClientIds", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ClientDetailsIds getAllClientIds() {
        return new ClientDetailsIds(oauthClientDetailsDao.getAllClientIds());
    }

}
