package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.TournamentMapper;
import com.venefast.springboot.soccer.app.models.dtos.TournamentDto;
import com.venefast.springboot.soccer.app.models.entities.Tournament;
import com.venefast.springboot.soccer.app.repositories.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Transactional(readOnly = true)
    public List<TournamentDto> findAll() {
        return tournamentRepository.findAll().stream()
                .map(tournamentMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public TournamentDto findById(Long id) {
        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id: " + id));
        return tournamentMapper.toDto(tournament);
    }

    @Transactional(readOnly = true)
    public List<TournamentDto> findByStatus(String status) {
        return tournamentRepository.findByStatus(status).stream()
                .map(tournamentMapper::toDto)
                .toList();
    }

    public TournamentDto create(TournamentDto dto) {
        Tournament tournament = tournamentMapper.toEntity(dto);
        tournament.setId(null);
        Tournament saved = tournamentRepository.save(tournament);
        return tournamentMapper.toDto(saved);
    }

    public TournamentDto update(Long id, TournamentDto dto) {
        Tournament existing = tournamentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found with id: " + id));

        existing.setTournamentName(dto.tournamentName());
        existing.setStartDate(dto.startDate());
        existing.setEndDate(dto.endDate());
        existing.setStatus(dto.status());

        Tournament updated = tournamentRepository.save(existing);
        return tournamentMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tournament not found with id: " + id);
        }
        tournamentRepository.deleteById(id);
    }
}
