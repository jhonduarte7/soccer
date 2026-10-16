package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.TournamentDto;
import com.venefast.springboot.soccer.app.models.entities.Tournament;
import org.springframework.stereotype.Component;

@Component
public class TournamentMapper {

    public TournamentDto toDto(Tournament entity) {
        if (entity == null) {
            return null;
        }
        return new TournamentDto(
            entity.getId(),
            entity.getTournamentName(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getStatus()
        );
    }

    public Tournament toEntity(TournamentDto dto) {
        if (dto == null) {
            return null;
        }
        Tournament tournament = new Tournament();
        tournament.setId(dto.id());
        tournament.setTournamentName(dto.tournamentName());
        tournament.setStartDate(dto.startDate());
        tournament.setEndDate(dto.endDate());
        tournament.setStatus(dto.status());
        return tournament;
    }
}
