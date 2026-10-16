package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.PlayerPositionDto;
import com.venefast.springboot.soccer.app.models.entities.Player;
import com.venefast.springboot.soccer.app.models.entities.PlayerPosition;
import com.venefast.springboot.soccer.app.models.entities.PlayerPositionId;
import org.springframework.stereotype.Component;

@Component
public class PlayerPositionMapper {

    public PlayerPositionDto toDto(PlayerPosition entity) {
        if (entity == null) {
            return null;
        }
        Long playerId = entity.getPlayer() != null ? entity.getPlayer().getId() : (entity.getId() != null ? entity.getId().getPlayerId() : null);
        String playerName = entity.getPlayer() != null ? entity.getPlayer().getFirstName() + " " + entity.getPlayer().getLastName() : null;
        String position = entity.getPosition();

        return new PlayerPositionDto(playerId, playerName, position);
    }

    public PlayerPosition toEntity(PlayerPositionDto dto, Player player) {
        if (dto == null) {
            return null;
        }
        PlayerPosition pp = new PlayerPosition();
        pp.setId(new PlayerPositionId(dto.playerId(), dto.position()));
        pp.setPlayer(player);
        return pp;
    }
}
