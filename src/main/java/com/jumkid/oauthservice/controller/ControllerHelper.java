package com.jumkid.oauthservice.controller;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ControllerHelper {

    public <T> T getPayloadValue(Map<String, Object> payload, String fieldName, Class<T> clazz) {
        if(payload.containsKey(fieldName) && payload.get(fieldName)!=null){
            return (T)payload.get(fieldName);
        }else if(clazz == Boolean.class){
            return (T)Boolean.FALSE;
        }
        return null;
    }

}
