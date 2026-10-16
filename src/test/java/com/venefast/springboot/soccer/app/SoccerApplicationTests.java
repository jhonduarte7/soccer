package com.venefast.springboot.soccer.app;

import com.venefast.springboot.soccer.app.repositories.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SoccerApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PlayerGuardianRepository playerGuardianRepository;

    @Autowired
    private PlayerPositionRepository playerPositionRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private TournamentMatchdayRepository tournamentMatchdayRepository;

    @Autowired
    private PaymentConceptRepository paymentConceptRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Test
    @DisplayName("Verify Spring application context loads and all tables have at least 50 records")
    void contextLoadsAndAllTablesHaveAtLeast50Records() {
        assertTrue(roleRepository.count() >= 50, "roles should have at least 50 records. Current: " + roleRepository.count());
        assertTrue(userRepository.count() >= 50, "usuarios should have at least 50 records. Current: " + userRepository.count());
        assertTrue(categoryRepository.count() >= 50, "categorias should have at least 50 records. Current: " + categoryRepository.count());
        assertTrue(playerRepository.count() >= 50, "jugadores should have at least 50 records. Current: " + playerRepository.count());
        assertTrue(guardianRepository.count() >= 50, "tutores should have at least 50 records. Current: " + guardianRepository.count());
        assertTrue(playerGuardianRepository.count() >= 50, "jugadores_tutores should have at least 50 records. Current: " + playerGuardianRepository.count());
        assertTrue(playerPositionRepository.count() >= 50, "jugador_posiciones should have at least 50 records. Current: " + playerPositionRepository.count());
        assertTrue(tournamentRepository.count() >= 50, "torneos should have at least 50 records. Current: " + tournamentRepository.count());
        assertTrue(tournamentMatchdayRepository.count() >= 50, "torneo_jornadas should have at least 50 records. Current: " + tournamentMatchdayRepository.count());
        assertTrue(paymentConceptRepository.count() >= 50, "conceptos_pago should have at least 50 records. Current: " + paymentConceptRepository.count());
        assertTrue(paymentRepository.count() >= 50, "pagos should have at least 50 records. Current: " + paymentRepository.count());
        assertTrue(newsRepository.count() >= 50, "noticias should have at least 50 records. Current: " + newsRepository.count());
    }
}
