package com.jumkid.oauthcentral.model.mapper;

import com.jumkid.oauthcentral.dto.User;
import com.jumkid.oauthcentral.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public class UserMapper extends CommonMapper<UserEntity, User> {

    @Override
    public User entityToDTO(UserEntity entity) {
        return entity != null ? User.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .active(entity.isActive())
                .build() : null;
    }

    @Override
    public UserEntity dtoToEntity(User dto) {
        return dto != null ? UserEntity.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .active(dto.isActive())
                .build() : null;
    }
}
