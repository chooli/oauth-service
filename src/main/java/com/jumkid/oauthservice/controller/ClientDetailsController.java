package com.jumkid.oauthservice.controller;

import com.jumkid.oauthservice.controller.response.CommonResponse;
import com.jumkid.oauthservice.dao.OauthClientDetailsDao;
import com.jumkid.oauthservice.model.ClientDetails;
import com.jumkid.oauthservice.model.ClientDetailsIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/clientdetails")
public class ClientDetailsController {

    private final OauthClientDetailsDao oauthClientDetailsDao;

    @Autowired
    public ClientDetailsController(OauthClientDetailsDao oauthClientDetailsDao) {
        this.oauthClientDetailsDao = oauthClientDetailsDao;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ClientDetails Add(@RequestBody Map<String, Object> payload){
        ClientDetails newEntity = new ClientDetails.Builder()
                .clientId((String)payload.get(ClientDetails.Fields.CLIENT_ID.value()))
                .resourceIds((String)payload.get(ClientDetails.Fields.RESOURCE_IDS.value()))
                .clientSecret((String)payload.get(ClientDetails.Fields.CLIENT_SECRET.value()))
                .scope((String)payload.get(ClientDetails.Fields.SCOPE.value()))
                .authorizedGrantTypes((String)payload.get(ClientDetails.Fields.AUTHORIZED_GRANT_TYPES.value()))
                .webServerRedirectUri((String)payload.get(ClientDetails.Fields.WEB_SERVER_REDIRECT_URI.value()))
                .authorities((String)payload.get(ClientDetails.Fields.AUTHORITIES.value()))
                .accessTokenValidity(getPayloadValue(payload, ClientDetails.Fields.ACCESS_TOKEN_VALIDITY.value(), Integer.class))
                .refreshTokenValidity(getPayloadValue(payload, ClientDetails.Fields.REFRESH_TOKEN_VALIDITY.value(), Integer.class))
                .additionalInformation((String)payload.get(ClientDetails.Fields.ADDITIONAL_INFORMATION.value()))
                .autoapprove(getPayloadValue(payload, ClientDetails.Fields.AUTO_APPROVE.value(), Boolean.class))
                .build();

        oauthClientDetailsDao.add(newEntity);

        return newEntity;
    }

    private <T> T getPayloadValue(Map<String, Object> payload, String fieldName, Class<T> clazz) {
        if(payload.containsKey(fieldName) && payload.get(fieldName)!=null){
            return (T)payload.get(fieldName);
        }else if(clazz == Boolean.class){
            return (T)Boolean.FALSE;
        }
        return null;
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ClientDetails get(@PathVariable String clientId) {
        return oauthClientDetailsDao.get(clientId);
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public CommonResponse updateProperty(@PathVariable String clientId,
                                         @RequestParam String fieldName,
                                         @RequestParam Object fieldValue) {
        if(clientId!=null && fieldName!=null && fieldValue!=null){
            int row = oauthClientDetailsDao.updateField(clientId, ClientDetails.Fields.getColumnName(fieldName), fieldValue);
            return new CommonResponse((row == 1), null);
        }
        return new CommonResponse(false, "Failed to update project value");
    }

    @RequestMapping(value = "/allClientIds", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ClientDetailsIds getAllClientIds() {
        return new ClientDetailsIds(oauthClientDetailsDao.getAllClientIds());
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public CommonResponse delete(@PathVariable String clientId) {
        if(clientId != null) {
            int row = oauthClientDetailsDao.remove(clientId);
            return new CommonResponse((row == 1), null);
        }
        return new CommonResponse(false, "Failed to delete client details, client id is invalid");
    }

}
