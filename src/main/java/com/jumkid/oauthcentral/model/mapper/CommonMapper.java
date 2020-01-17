package com.jumkid.oauthcentral.model.mapper;

public abstract class CommonMapper<E, T> {

    public abstract T entityToDTO(E entity);

    public abstract E dtoToEntity(T dto);

}
