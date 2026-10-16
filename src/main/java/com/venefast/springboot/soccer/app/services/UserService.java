package com.venefast.springboot.soccer.app.services;

import com.venefast.springboot.soccer.app.exceptions.ResourceNotFoundException;
import com.venefast.springboot.soccer.app.mappers.UserMapper;
import com.venefast.springboot.soccer.app.models.dtos.UserDto;
import com.venefast.springboot.soccer.app.models.entities.Role;
import com.venefast.springboot.soccer.app.models.entities.User;
import com.venefast.springboot.soccer.app.repositories.RoleRepository;
import com.venefast.springboot.soccer.app.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    public UserDto create(UserDto dto) {
        Role role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + dto.roleId()));
        User user = userMapper.toEntity(dto, role);
        user.setId(null);
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword("secret123");
        }
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    public UserDto update(Long id, UserDto dto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        Role role = roleRepository.findById(dto.roleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + dto.roleId()));

        existing.setUsername(dto.username());
        existing.setEmail(dto.email());
        existing.setRole(role);
        if (dto.password() != null && !dto.password().isBlank()) {
            existing.setPassword(dto.password());
        }
        User updated = userRepository.save(existing);
        return userMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
