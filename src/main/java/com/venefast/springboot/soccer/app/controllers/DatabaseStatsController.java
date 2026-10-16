package com.venefast.springboot.soccer.app.controllers;

import com.venefast.springboot.soccer.app.repositories.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class DatabaseStatsController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PlayerRepository playerRepository;
    private final GuardianRepository guardianRepository;
    private final PlayerGuardianRepository playerGuardianRepository;
    private final PlayerPositionRepository playerPositionRepository;
    private final TournamentRepository tournamentRepository;
    private final TournamentMatchdayRepository tournamentMatchdayRepository;
    private final PaymentConceptRepository paymentConceptRepository;
    private final PaymentRepository paymentRepository;
    private final NewsRepository newsRepository;

    public DatabaseStatsController(RoleRepository roleRepository,
                                   UserRepository userRepository,
                                   CategoryRepository categoryRepository,
                                   PlayerRepository playerRepository,
                                   GuardianRepository guardianRepository,
                                   PlayerGuardianRepository playerGuardianRepository,
                                   PlayerPositionRepository playerPositionRepository,
                                   TournamentRepository tournamentRepository,
                                   TournamentMatchdayRepository tournamentMatchdayRepository,
                                   PaymentConceptRepository paymentConceptRepository,
                                   PaymentRepository paymentRepository,
                                   NewsRepository newsRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.playerRepository = playerRepository;
        this.guardianRepository = guardianRepository;
        this.playerGuardianRepository = playerGuardianRepository;
        this.playerPositionRepository = playerPositionRepository;
        this.tournamentRepository = tournamentRepository;
        this.tournamentMatchdayRepository = tournamentMatchdayRepository;
        this.paymentConceptRepository = paymentConceptRepository;
        this.paymentRepository = paymentRepository;
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getDatabaseStats() {
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("roles", roleRepository.count());
        stats.put("usuarios", userRepository.count());
        stats.put("categorias", categoryRepository.count());
        stats.put("jugadores", playerRepository.count());
        stats.put("tutores", guardianRepository.count());
        stats.put("jugadores_tutores", playerGuardianRepository.count());
        stats.put("jugador_posiciones", playerPositionRepository.count());
        stats.put("torneos", tournamentRepository.count());
        stats.put("torneo_jornadas", tournamentMatchdayRepository.count());
        stats.put("conceptos_pago", paymentConceptRepository.count());
        stats.put("pagos", paymentRepository.count());
        stats.put("noticias", newsRepository.count());

        boolean allAtLeast50 = stats.values().stream()
                .map(val -> (Long) val)
                .allMatch(count -> count >= 50);
        stats.put("all_tables_meet_50_records_target", allAtLeast50);

        return ResponseEntity.ok(stats);
    }
}
