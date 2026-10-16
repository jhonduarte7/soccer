package com.venefast.springboot.soccer.app.controllers;

import com.venefast.springboot.soccer.app.models.dtos.PlayerGuardianDto;
import com.venefast.springboot.soccer.app.services.PlayerGuardianService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-guardians")
@CrossOrigin(origins = "*")
public class PlayerGuardianController {

    private final PlayerGuardianService playerGuardianService;

    public PlayerGuardianController(PlayerGuardianService playerGuardianService) {
        this.playerGuardianService = playerGuardianService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerGuardianDto>> getAll() {
        return ResponseEntity.ok(playerGuardianService.findAll());
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerGuardianDto>> getByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerGuardianService.findByPlayerId(playerId));
    }

    @GetMapping("/{playerId}/{guardianId}")
    public ResponseEntity<PlayerGuardianDto> getById(@PathVariable Long playerId, @PathVariable Long guardianId) {
        return ResponseEntity.ok(playerGuardianService.findById(playerId, guardianId));
    }

    @PostMapping
    public ResponseEntity<PlayerGuardianDto> create(@Valid @RequestBody PlayerGuardianDto dto) {
        PlayerGuardianDto created = playerGuardianService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{playerId}/{guardianId}")
    public ResponseEntity<PlayerGuardianDto> update(@PathVariable Long playerId,
                                                    @PathVariable Long guardianId,
                                                    @Valid @RequestBody PlayerGuardianDto dto) {
        return ResponseEntity.ok(playerGuardianService.update(playerId, guardianId, dto));
    }

    @DeleteMapping("/{playerId}/{guardianId}")
    public ResponseEntity<Void> delete(@PathVariable Long playerId, @PathVariable Long guardianId) {
        playerGuardianService.delete(playerId, guardianId);
        return ResponseEntity.noContent().build();
    }
}
