package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.PlayerGuardianDto;
import com.venefast.springboot.soccer.app.models.entities.Guardian;
import com.venefast.springboot.soccer.app.models.entities.Player;
import com.venefast.springboot.soccer.app.models.entities.PlayerGuardian;
import com.venefast.springboot.soccer.app.models.entities.PlayerGuardianId;
import org.springframework.stereotype.Component;

@Component
public class PlayerGuardianMapper {

    public PlayerGuardianDto toDto(PlayerGuardian entity) {
        if (entity == null) {
            return null;
        }
        Long playerId = entity.getPlayer() != null ? entity.getPlayer().getId() : (entity.getId() != null ? entity.getId().getPlayerId() : null);
        String playerName = entity.getPlayer() != null ? entity.getPlayer().getFirstName() + " " + entity.getPlayer().getLastName() : null;
        Long guardianId = entity.getGuardian() != null ? entity.getGuardian().getId() : (entity.getId() != null ? entity.getId().getGuardianId() : null);
        String guardianName = entity.getGuardian() != null ? entity.getGuardian().getFullName() : null;

        return new PlayerGuardianDto(
            playerId,
            playerName,
            guardianId,
            guardianName,
            entity.getRelationship()
        );
    }

    public PlayerGuardian toEntity(PlayerGuardianDto dto, Player player, Guardian guardian) {
        if (dto == null) {
            return null;
        }
        PlayerGuardian pg = new PlayerGuardian();
        pg.setId(new PlayerGuardianId(dto.playerId(), dto.guardianId()));
        pg.setPlayer(player);
        pg.setGuardian(guardian);
        pg.setRelationship(dto.relationship());
        return pg;
    }
}
