package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.UserDto;
import com.venefast.springboot.soccer.app.models.entities.Role;
import com.venefast.springboot.soccer.app.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User entity) {
        if (entity == null) {
            return null;
        }
        Long roleId = entity.getRole() != null ? entity.getRole().getId() : null;
        String roleName = entity.getRole() != null ? entity.getRole().getName() : null;
        return new UserDto(
            entity.getId(),
            entity.getUsername(),
            null, // Do not expose password
            entity.getEmail(),
            roleId,
            roleName
        );
    }

    public User toEntity(UserDto dto, Role role) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setId(dto.id());
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setRole(role);
        return user;
    }
}
