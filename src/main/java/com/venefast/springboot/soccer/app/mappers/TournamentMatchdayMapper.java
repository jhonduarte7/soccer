package com.venefast.springboot.soccer.app.mappers;

import com.venefast.springboot.soccer.app.models.dtos.TournamentMatchdayDto;
import com.venefast.springboot.soccer.app.models.entities.Tournament;
import com.venefast.springboot.soccer.app.models.entities.TournamentMatchday;
import org.springframework.stereotype.Component;

@Component
public class TournamentMatchdayMapper {

    public TournamentMatchdayDto toDto(TournamentMatchday entity) {
        if (entity == null) {
            return null;
        }
        Long tournamentId = entity.getTournament() != null ? entity.getTournament().getId() : null;
        String tournamentName = entity.getTournament() != null ? entity.getTournament().getTournamentName() : null;

        return new TournamentMatchdayDto(
            entity.getId(),
            tournamentId,
            tournamentName,
            entity.getHomeTeam(),
            entity.getAwayTeam(),
            entity.getMatchDateTime(),
            entity.getLocation()
        );
    }

    public TournamentMatchday toEntity(TournamentMatchdayDto dto, Tournament tournament) {
        if (dto == null) {
            return null;
        }
        TournamentMatchday matchday = new TournamentMatchday();
        matchday.setId(dto.id());
        matchday.setTournament(tournament);
        matchday.setHomeTeam(dto.homeTeam());
        matchday.setAwayTeam(dto.awayTeam());
        matchday.setMatchDateTime(dto.matchDateTime());
        matchday.setLocation(dto.location());
        return matchday;
    }
}
