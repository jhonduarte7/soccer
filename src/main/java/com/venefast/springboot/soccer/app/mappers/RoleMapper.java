package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.RoleDto;
import com.venefast.springboot.soccer.app.models.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDto toDto(Role entity) {
        if (entity == null) {
            return null;
        }
        return new RoleDto(entity.getId(), entity.getName());
    }

    public Role toEntity(RoleDto dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(dto.id());
        role.setName(dto.name());
        return role;
    }
}
