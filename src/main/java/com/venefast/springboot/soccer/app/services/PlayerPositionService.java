package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.PlayerPositionMapper;
import com.venefast.springboot.soccer.app.models.dtos.PlayerPositionDto;
import com.venefast.springboot.soccer.app.models.entities.Player;
import com.venefast.springboot.soccer.app.models.entities.PlayerPosition;
import com.venefast.springboot.soccer.app.models.entities.PlayerPositionId;
import com.venefast.springboot.soccer.app.repositories.PlayerPositionRepository;
import com.venefast.springboot.soccer.app.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerPositionService {

    private final PlayerPositionRepository playerPositionRepository;
    private final PlayerRepository playerRepository;
    private final PlayerPositionMapper playerPositionMapper;

    public PlayerPositionService(PlayerPositionRepository playerPositionRepository,
                                 PlayerRepository playerRepository,
                                 PlayerPositionMapper playerPositionMapper) {
        this.playerPositionRepository = playerPositionRepository;
        this.playerRepository = playerRepository;
        this.playerPositionMapper = playerPositionMapper;
    }

    @Transactional(readOnly = true)
    public List<PlayerPositionDto> findAll() {
        return playerPositionRepository.findAll().stream()
                .map(playerPositionMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerPositionDto findById(Long playerId, String position) {
        PlayerPositionId id = new PlayerPositionId(playerId, position);
        PlayerPosition pp = playerPositionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player position not found for player: " + playerId + " and position: " + position));
        return playerPositionMapper.toDto(pp);
    }

    @Transactional(readOnly = true)
    public List<PlayerPositionDto> findByPlayerId(Long playerId) {
        return playerPositionRepository.findByIdPlayerId(playerId).stream()
                .map(playerPositionMapper::toDto)
                .toList();
    }

    public PlayerPositionDto create(PlayerPositionDto dto) {
        Player player = playerRepository.findById(dto.playerId())
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + dto.playerId()));

        PlayerPosition pp = playerPositionMapper.toEntity(dto, player);
        PlayerPosition saved = playerPositionRepository.save(pp);
        return playerPositionMapper.toDto(saved);
    }

    public void delete(Long playerId, String position) {
        PlayerPositionId id = new PlayerPositionId(playerId, position);
        if (!playerPositionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player position not found for player: " + playerId + " and position: " + position);
        }
        playerPositionRepository.deleteById(id);
    }
}
