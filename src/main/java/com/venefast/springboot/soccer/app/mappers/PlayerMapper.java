package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.PlayerDto;
import com.venefast.springboot.soccer.app.models.entities.Category;
import com.venefast.springboot.soccer.app.models.entities.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public PlayerDto toDto(Player entity) {
        if (entity == null) {
            return null;
        }
        Long categoryId = entity.getCategory() != null ? entity.getCategory().getId() : null;
        String categoryName = entity.getCategory() != null ? entity.getCategory().getName() : null;
        return new PlayerDto(
            entity.getId(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBirthDate(),
            entity.getRegistrationDate(),
            categoryId,
            categoryName
        );
    }

    public Player toEntity(PlayerDto dto, Category category) {
        if (dto == null) {
            return null;
        }
        Player player = new Player();
        player.setId(dto.id());
        player.setFirstName(dto.firstName());
        player.setLastName(dto.lastName());
        player.setBirthDate(dto.birthDate());
        player.setRegistrationDate(dto.registrationDate());
        player.setCategory(category);
        return player;
    }
}
