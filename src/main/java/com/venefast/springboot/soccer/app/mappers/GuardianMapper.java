package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.GuardianDto;
import com.venefast.springboot.soccer.app.models.entities.Guardian;
import org.springframework.stereotype.Component;

@Component
public class GuardianMapper {

    public GuardianDto toDto(Guardian entity) {
        if (entity == null) {
            return null;
        }
        return new GuardianDto(
            entity.getId(),
            entity.getFullName(),
            entity.getPhone(),
            entity.getEmail()
        );
    }

    public Guardian toEntity(GuardianDto dto) {
        if (dto == null) {
            return null;
        }
        Guardian guardian = new Guardian();
        guardian.setId(dto.id());
        guardian.setFullName(dto.fullName());
        guardian.setPhone(dto.phone());
        guardian.setEmail(dto.email());
        return guardian;
    }
}
