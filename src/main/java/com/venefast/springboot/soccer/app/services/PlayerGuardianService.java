package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.PlayerGuardianMapper;
import com.venefast.springboot.soccer.app.models.dtos.PlayerGuardianDto;
import com.venefast.springboot.soccer.app.models.entities.Guardian;
import com.venefast.springboot.soccer.app.models.entities.Player;
import com.venefast.springboot.soccer.app.models.entities.PlayerGuardian;
import com.venefast.springboot.soccer.app.models.entities.PlayerGuardianId;
import com.venefast.springboot.soccer.app.repositories.GuardianRepository;
import com.venefast.springboot.soccer.app.repositories.PlayerGuardianRepository;
import com.venefast.springboot.soccer.app.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerGuardianService {

    private final PlayerGuardianRepository playerGuardianRepository;
    private final PlayerRepository playerRepository;
    private final GuardianRepository guardianRepository;
    private final PlayerGuardianMapper playerGuardianMapper;

    public PlayerGuardianService(PlayerGuardianRepository playerGuardianRepository,
                                 PlayerRepository playerRepository,
                                 GuardianRepository guardianRepository,
                                 PlayerGuardianMapper playerGuardianMapper) {
        this.playerGuardianRepository = playerGuardianRepository;
        this.playerRepository = playerRepository;
        this.guardianRepository = guardianRepository;
        this.playerGuardianMapper = playerGuardianMapper;
    }

    @Transactional(readOnly = true)
    public List<PlayerGuardianDto> findAll() {
        return playerGuardianRepository.findAll().stream()
                .map(playerGuardianMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerGuardianDto findById(Long playerId, Long guardianId) {
        PlayerGuardianId id = new PlayerGuardianId(playerId, guardianId);
        PlayerGuardian pg = playerGuardianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player-Guardian relationship not found for player: " + playerId + " and guardian: " + guardianId));
        return playerGuardianMapper.toDto(pg);
    }

    @Transactional(readOnly = true)
    public List<PlayerGuardianDto> findByPlayerId(Long playerId) {
        return playerGuardianRepository.findByIdPlayerId(playerId).stream()
                .map(playerGuardianMapper::toDto)
                .toList();
    }

    public PlayerGuardianDto create(PlayerGuardianDto dto) {
        Player player = playerRepository.findById(dto.playerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + dto.playerId()));
        Guardian guardian = guardianRepository.findById(dto.guardianId())
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with id: " + dto.guardianId()));

        PlayerGuardian pg = playerGuardianMapper.toEntity(dto, player, guardian);
        PlayerGuardian saved = playerGuardianRepository.save(pg);
        return playerGuardianMapper.toDto(saved);
    }

    public PlayerGuardianDto update(Long playerId, Long guardianId, PlayerGuardianDto dto) {
        PlayerGuardianId id = new PlayerGuardianId(playerId, guardianId);
        PlayerGuardian existing = playerGuardianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player-Guardian relationship not found for player: " + playerId + " and guardian: " + guardianId));

        existing.setRelationship(dto.relationship());
        PlayerGuardian updated = playerGuardianRepository.save(existing);
        return playerGuardianMapper.toDto(updated);
    }

    public void delete(Long playerId, Long guardianId) {
        PlayerGuardianId id = new PlayerGuardianId(playerId, guardianId);
        if (!playerGuardianRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player-Guardian relationship not found for player: " + playerId + " and guardian: " + guardianId);
        }
        playerGuardianRepository.deleteById(id);
    }
}
