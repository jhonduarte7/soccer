package com.venefast.springboot.soccer.app.controllers;

import com.venefast.springboot.soccer.app.models.dtos.GuardianDto;
import com.venefast.springboot.soccer.app.services.GuardianService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guardians")
@CrossOrigin(origins = "*")
public class GuardianController {

    private final GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @GetMapping
    public ResponseEntity<List<GuardianDto>> getAll() {
        return ResponseEntity.ok(guardianService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuardianDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(guardianService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GuardianDto> create(@Valid @RequestBody GuardianDto dto) {
        GuardianDto created = guardianService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuardianDto> update(@PathVariable Long id, @Valid @RequestBody GuardianDto dto) {
        return ResponseEntity.ok(guardianService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        guardianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
