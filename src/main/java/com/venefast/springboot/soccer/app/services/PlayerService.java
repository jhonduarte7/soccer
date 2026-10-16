package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.PlayerMapper;
import com.venefast.springboot.soccer.app.models.dtos.PlayerDto;
import com.venefast.springboot.soccer.app.models.entities.Category;
import com.venefast.springboot.soccer.app.models.entities.Player;
import com.venefast.springboot.soccer.app.repositories.CategoryRepository;
import com.venefast.springboot.soccer.app.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final CategoryRepository categoryRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, CategoryRepository categoryRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.categoryRepository = categoryRepository;
        this.playerMapper = playerMapper;
    }

    @Transactional(readOnly = true)
    public List<PlayerDto> findAll() {
        return playerRepository.findAll().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlayerDto findById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
        return playerMapper.toDto(player);
    }

    @Transactional(readOnly = true)
    public List<PlayerDto> findByCategoryId(Long categoryId) {
        return playerRepository.findByCategoryId(categoryId).stream()
                .map(playerMapper::toDto)
                .toList();
    }

    public PlayerDto create(PlayerDto dto) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.categoryId()));
        Player player = playerMapper.toEntity(dto, category);
        player.setId(null);
        Player saved = playerRepository.save(player);
        return playerMapper.toDto(saved);
    }

    public PlayerDto update(Long id, PlayerDto dto) {
        Player existing = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.categoryId()));

        existing.setFirstName(dto.firstName());
        existing.setLastName(dto.lastName());
        existing.setBirthDate(dto.birthDate());
        existing.setRegistrationDate(dto.registrationDate());
        existing.setCategory(category);

        Player updated = playerRepository.save(existing);
        return playerMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }
}
