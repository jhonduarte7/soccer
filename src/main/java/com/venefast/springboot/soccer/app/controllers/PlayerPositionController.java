package com.venefast.springboot.soccer.app.controllers;

import com.venefast.springboot.soccer.app.models.dtos.PlayerPositionDto;
import com.venefast.springboot.soccer.app.services.PlayerPositionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player-positions")
@CrossOrigin(origins = "*")
public class PlayerPositionController {

    private final PlayerPositionService playerPositionService;

    public PlayerPositionController(PlayerPositionService playerPositionService) {
        this.playerPositionService = playerPositionService;
    }

    @GetMapping
    public ResponseEntity<List<PlayerPositionDto>> getAll() {
        return ResponseEntity.ok(playerPositionService.findAll());
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PlayerPositionDto>> getByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerPositionService.findByPlayerId(playerId));
    }

    @GetMapping("/{playerId}/{position}")
    public ResponseEntity<PlayerPositionDto> getById(@PathVariable Long playerId, @PathVariable String position) {
        return ResponseEntity.ok(playerPositionService.findById(playerId, position));
    }

    @PostMapping
    public ResponseEntity<PlayerPositionDto> create(@Valid @RequestBody PlayerPositionDto dto) {
        PlayerPositionDto created = playerPositionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{playerId}/{position}")
    public ResponseEntity<Void> delete(@PathVariable Long playerId, @PathVariable String position) {
        playerPositionService.delete(playerId, position);
        return ResponseEntity.noContent().build();
    }
}
