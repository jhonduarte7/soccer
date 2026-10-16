package com.venefast.springboot.soccer.app.controllers;

import com.venefast.springboot.soccer.app.models.dtos.TournamentDto;
import com.venefast.springboot.soccer.app.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@CrossOrigin(origins = "*")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public ResponseEntity<List<TournamentDto>> getAll(@RequestParam(required = false) String status) {
        if (status != null) {
            return ResponseEntity.ok(tournamentService.findByStatus(status));
        }
        return ResponseEntity.ok(tournamentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TournamentDto> create(@Valid @RequestBody TournamentDto dto) {
        TournamentDto created = tournamentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentDto> update(@PathVariable Long id, @Valid @RequestBody TournamentDto dto) {
        return ResponseEntity.ok(tournamentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
