package com.jumkid.oauthservice.controller;

import com.jumkid.oauthservice.controller.response.CommonResponse;
import com.jumkid.oauthservice.dao.ClientDetailsDao;
import com.jumkid.oauthservice.model.ClientDetails;
import com.jumkid.oauthservice.model.ClientDetailsIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.jumkid.oauthservice.controller.response.CommonResponse.ErrorCodes.*;

@Controller
@RequestMapping("/clientdetails")
public class ClientDetailsController {

    private final ClientDetailsDao clientDetailsDao;

    private final ControllerHelper controllerHelper;

    @Autowired
    public ClientDetailsController(ClientDetailsDao clientDetailsDao, ControllerHelper controllerHelper) {
        this.clientDetailsDao = clientDetailsDao;
        this.controllerHelper = controllerHelper;
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
                .accessTokenValidity(controllerHelper.getPayloadValue(payload, ClientDetails.Fields.ACCESS_TOKEN_VALIDITY.value(), Integer.class))
                .refreshTokenValidity(controllerHelper.getPayloadValue(payload, ClientDetails.Fields.REFRESH_TOKEN_VALIDITY.value(), Integer.class))
                .additionalInformation((String)payload.get(ClientDetails.Fields.ADDITIONAL_INFORMATION.value()))
                .autoapprove(controllerHelper.getPayloadValue(payload, ClientDetails.Fields.AUTO_APPROVE.value(), Boolean.class))
                .build();

        clientDetailsDao.add(newEntity);

        return newEntity;
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ClientDetails get(@PathVariable String clientId) {
        return clientDetailsDao.get(clientId);
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public CommonResponse updateProperties(@PathVariable String clientId,
                                           @RequestBody Map<String, Object> properties) {
        //validate client id
        if(clientId == null) return new CommonResponse(ERROR_VALIDATION.code(), "client id is empty");
        //validate properties
        if(properties == null || properties.isEmpty()) return new CommonResponse(ERROR_VALIDATION.code(), "no property to be updated");

        if(clientDetailsDao.updateFields(clientId, properties) == 1) {
            return new CommonResponse("properties are updated");
        }
        return new CommonResponse(ERROR_DB.code(), "failed to update properties");
    }

    @RequestMapping(value = "/allClientIds", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ClientDetailsIds getAllClientIds() {
        return new ClientDetailsIds(clientDetailsDao.getAllClientIds());
    }

    @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public CommonResponse delete(@PathVariable String clientId) {
        //validate client id
        if(clientId == null) return new CommonResponse(ERROR_VALIDATION.code(), "client id is empty");

        if(clientDetailsDao.remove(clientId) == 1) {
            return new CommonResponse("client details is deleted");
        }
        return new CommonResponse(ERROR_DB.code(), "failed to delete client details");
    }

}
