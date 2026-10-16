package com.venefast.springboot.soccer.app.controllers;

import com.venefast.springboot.soccer.app.models.dtos.TournamentMatchdayDto;
import com.venefast.springboot.soccer.app.services.TournamentMatchdayService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournament-matchdays")
@CrossOrigin(origins = "*")
public class TournamentMatchdayController {

    private final TournamentMatchdayService tournamentMatchdayService;

    public TournamentMatchdayController(TournamentMatchdayService tournamentMatchdayService) {
        this.tournamentMatchdayService = tournamentMatchdayService;
    }

    @GetMapping
    public ResponseEntity<List<TournamentMatchdayDto>> getAll(@RequestParam(required = false) Long tournamentId) {
        if (tournamentId != null) {
            return ResponseEntity.ok(tournamentMatchdayService.findByTournamentId(tournamentId));
        }
        return ResponseEntity.ok(tournamentMatchdayService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentMatchdayDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tournamentMatchdayService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TournamentMatchdayDto> create(@Valid @RequestBody TournamentMatchdayDto dto) {
        TournamentMatchdayDto created = tournamentMatchdayService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentMatchdayDto> update(@PathVariable Long id, @Valid @RequestBody TournamentMatchdayDto dto) {
        return ResponseEntity.ok(tournamentMatchdayService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tournamentMatchdayService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
