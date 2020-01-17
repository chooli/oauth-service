package com.jumkid.oauthcentral.model.mapper;

import com.jumkid.oauthcentral.dto.Authority;
import com.jumkid.oauthcentral.model.AuthorityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public class AuthorityMapper extends CommonMapper<AuthorityEntity, Authority> {
    @Override
    public Authority entityToDTO(AuthorityEntity entity) {
        return entity != null ? Authority.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .role(entity.getRole())
                .build() : null;
    }

    @Override
    public AuthorityEntity dtoToEntity(Authority dto) {
        return dto != null ? AuthorityEntity.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .role(dto.getRole())
                .build() : null;
    }
}
