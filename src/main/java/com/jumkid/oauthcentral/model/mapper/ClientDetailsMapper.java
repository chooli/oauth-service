package com.jumkid.oauthcentral.model.mapper;

import com.jumkid.oauthcentral.controller.dto.ClientDetails;
import com.jumkid.oauthcentral.model.ClientDetailsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ClientDetailsMapper extends CommonMapper<ClientDetailsEntity, ClientDetails>{

}
