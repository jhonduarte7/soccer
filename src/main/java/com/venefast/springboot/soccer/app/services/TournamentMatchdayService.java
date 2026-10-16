package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.TournamentMatchdayMapper;
import com.venefast.springboot.soccer.app.models.dtos.TournamentMatchdayDto;
import com.venefast.springboot.soccer.app.models.entities.Tournament;
import com.venefast.springboot.soccer.app.models.entities.TournamentMatchday;
import com.venefast.springboot.soccer.app.repositories.TournamentMatchdayRepository;
import com.venefast.springboot.soccer.app.repositories.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TournamentMatchdayService {

    private final TournamentMatchdayRepository tournamentMatchdayRepository;
    private final TournamentRepository tournamentRepository;
    private final TournamentMatchdayMapper tournamentMatchdayMapper;

    public TournamentMatchdayService(TournamentMatchdayRepository tournamentMatchdayRepository,
                                   TournamentRepository tournamentRepository,
                                   TournamentMatchdayMapper tournamentMatchdayMapper) {
        this.tournamentMatchdayRepository = tournamentMatchdayRepository;
        this.tournamentRepository = tournamentRepository;
        this.tournamentMatchdayMapper = tournamentMatchdayMapper;
    }

    @Transactional(readOnly = true)
    public List<TournamentMatchdayDto> findAll() {
        return tournamentMatchdayRepository.findAll().stream()
                .map(tournamentMatchdayMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public TournamentMatchdayDto findById(Long id) {
        TournamentMatchday matchday = tournamentMatchdayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament matchday not found with id: " + id));
        return tournamentMatchdayMapper.toDto(matchday);
    }

    @Transactional(readOnly = true)
    public List<TournamentMatchdayDto> findByTournamentId(Long tournamentId) {
        return tournamentMatchdayRepository.findByTournamentId(tournamentId).stream()
                .map(tournamentMatchdayMapper::toDto)
                .toList();
    }

    public TournamentMatchdayDto create(TournamentMatchdayDto dto) {
        Tournament tournament = tournamentRepository.findById(dto.tournamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id: " + dto.tournamentId()));

        TournamentMatchday matchday = tournamentMatchdayMapper.toEntity(dto, tournament);
        matchday.setId(null);
        TournamentMatchday saved = tournamentMatchdayRepository.save(matchday);
        return tournamentMatchdayMapper.toDto(saved);
    }

    public TournamentMatchdayDto update(Long id, TournamentMatchdayDto dto) {
        TournamentMatchday existing = tournamentMatchdayRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament matchday not found with id: " + id));
        Tournament tournament = tournamentRepository.findById(dto.tournamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id: " + dto.tournamentId()));

        existing.setTournament(tournament);
        existing.setHomeTeam(dto.homeTeam());
        existing.setAwayTeam(dto.awayTeam());
        existing.setMatchDateTime(dto.matchDateTime());
        existing.setLocation(dto.location());

        TournamentMatchday updated = tournamentMatchdayRepository.save(existing);
        return tournamentMatchdayMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!tournamentMatchdayRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tournament matchday not found with id: " + id);
        }
        tournamentMatchdayRepository.deleteById(id);
    }
}
