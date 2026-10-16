package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.GuardianMapper;
import com.venefast.springboot.soccer.app.models.dtos.GuardianDto;
import com.venefast.springboot.soccer.app.models.entities.Guardian;
import com.venefast.springboot.soccer.app.repositories.GuardianRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final GuardianMapper guardianMapper;

    public GuardianService(GuardianRepository guardianRepository, GuardianMapper guardianMapper) {
        this.guardianRepository = guardianRepository;
        this.guardianMapper = guardianMapper;
    }

    @Transactional(readOnly = true)
    public List<GuardianDto> findAll() {
        return guardianRepository.findAll().stream()
                .map(guardianMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public GuardianDto findById(Long id) {
        Guardian guardian = guardianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with id: " + id));
        return guardianMapper.toDto(guardian);
    }

    public GuardianDto create(GuardianDto dto) {
        Guardian guardian = guardianMapper.toEntity(dto);
        guardian.setId(null);
        Guardian saved = guardianRepository.save(guardian);
        return guardianMapper.toDto(saved);
    }

    public GuardianDto update(Long id, GuardianDto dto) {
        Guardian existing = guardianRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guardian not found with id: " + id));
        existing.setFullName(dto.fullName());
        existing.setPhone(dto.phone());
        existing.setEmail(dto.email());
        Guardian updated = guardianRepository.save(existing);
        return guardianMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!guardianRepository.existsById(id)) {
            throw new ResourceNotFoundException("Guardian not found with id: " + id);
        }
        guardianRepository.deleteById(id);
    }
}
