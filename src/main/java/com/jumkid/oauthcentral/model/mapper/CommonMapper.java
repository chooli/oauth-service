package com.jumkid.oauthcentral.model.mapper;

public interface CommonMapper<E, T> {

    T entityToDTO(E entity);

    E dtoToEntity(T dto);

}
