package com.venefast.springboot.soccer.app.config;

import com.venefast.springboot.soccer.app.models.entities.*;
import com.venefast.springboot.soccer.app.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

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

    public DataLoader(RoleRepository roleRepository,
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

    @Override
    @Transactional
    public void run(String... args) {
        if (roleRepository.count() > 0) {
            log.info("Database already seeded. Skipping initial data load.");
            return;
        }

        log.info("Starting mock data seeding: generating at least 50 records per table...");

        // 1. Roles (52 records)
        List<Role> roles = seedRoles();

        // 2. Users (55 records)
        List<User> users = seedUsers(roles);

        // 3. Categories (52 records)
        List<Category> categories = seedCategories();

        // 4. Players (55 records)
        List<Player> players = seedPlayers(categories);

        // 5. Guardians (55 records)
        List<Guardian> guardians = seedGuardians();

        // 6. Players_Guardians (4NF) (55 records)
        seedPlayerGuardians(players, guardians);

        // 7. Player_Positions (4NF) (60 records)
        seedPlayerPositions(players);

        // 8. Tournaments (52 records)
        List<Tournament> tournaments = seedTournaments();

        // 9. Tournament Matchdays (55 records)
        seedTournamentMatchdays(tournaments);

        // 10. Payment Concepts (52 records)
        List<PaymentConcept> concepts = seedPaymentConcepts();

        // 11. Payments (55 records)
        seedPayments(players, concepts, users);

        // 12. News (52 records)
        seedNews(users);

        logTableCounts();
        log.info("Mock data initialization completed successfully!");
    }

    private List<Role> seedRoles() {
        String[] roleNames = {
            "ROLE_ADMIN", "ROLE_USER", "ROLE_HEAD_COACH", "ROLE_ASSISTANT_COACH", "ROLE_GOALKEEPER_COACH",
            "ROLE_FITNESS_TRAINER", "ROLE_PHYSIOTHERAPIST", "ROLE_DOCTOR", "ROLE_NUTRITIONIST", "ROLE_PSYCHOLOGIST",
            "ROLE_SCOUT", "ROLE_ACADEMY_DIRECTOR", "ROLE_PRESIDENT", "ROLE_TREASURER", "ROLE_SECRETARY_GENERAL",
            "ROLE_LOGISTICS_MANAGER", "ROLE_EQUIPMENT_MANAGER", "ROLE_MEDIA_OFFICER", "ROLE_PHOTOGRAPHER", "ROLE_SECURITY_CHIEF",
            "ROLE_TACTICAL_ANALYST", "ROLE_VIDEO_ANALYST", "ROLE_DATA_SCIENTIST", "ROLE_COORDINATOR_SUB06", "ROLE_COORDINATOR_SUB08",
            "ROLE_COORDINATOR_SUB10", "ROLE_COORDINATOR_SUB12", "ROLE_COORDINATOR_SUB14", "ROLE_COORDINATOR_SUB16", "ROLE_COORDINATOR_SUB18",
            "ROLE_COORDINATOR_SUB20", "ROLE_REFEREE_OFFICIAL", "ROLE_DELEGATE_MATCH", "ROLE_FIELD_MARSHAL", "ROLE_SPONSOR_RELATIONS",
            "ROLE_COMMUNITY_MANAGER", "ROLE_EVENT_ORGANIZER", "ROLE_ACCOUNTS_RECEIVABLE", "ROLE_ACCOUNTS_PAYABLE", "ROLE_AUDITOR",
            "ROLE_TICKETING_OFFICER", "ROLE_VOLUNTEER_CAPTAIN", "ROLE_TUTOR_REPRESENTATIVE", "ROLE_LEGAL_COUNSEL", "ROLE_OPERATIONS_DIRECTOR",
            "ROLE_CANTEEN_STAFF", "ROLE_MAINTENANCE_SUPERVISOR", "ROLE_FACILITY_MANAGER", "ROLE_FIRST_AID_OFFICER", "ROLE_HONORARY_MEMBER",
            "ROLE_GUEST_SCOUT", "ROLE_DISCIPLINARY_COMMITTEE"
        };

        List<Role> roleList = new ArrayList<>();
        for (String rName : roleNames) {
            roleList.add(roleRepository.save(new Role(rName)));
        }
        return roleList;
    }

    private List<User> seedUsers(List<Role> roles) {
        List<User> userList = new ArrayList<>();
        String[] firstNames = {"carlos", "marcos", "lucas", "sofia", "valentina", "mateo", "daniel", "camila", "alejandro", "elena", "javier"};
        String[] lastNames = {"rodriguez", "gonzalez", "fernandez", "lopez", "martinez", "sanchez", "perez", "gomez", "martin", "jimenez"};

        for (int i = 1; i <= 55; i++) {
            String fName = firstNames[(i - 1) % firstNames.length];
            String lName = lastNames[(i - 1) % lastNames.length];
            String username = fName + "." + lName + i;
            String email = username + "@soccernet.com";
            Role role = roles.get((i - 1) % roles.size());

            User user = new User();
            user.setUsername(username);
            user.setPassword("$2a$10$wK2V78Ufkx1L/P7hPqYf9egY41hXJ8B2Y4U3zPj9QG1R3.V7aYfDe"); // "Secret123!"
            user.setEmail(email);
            user.setRole(role);
            userList.add(userRepository.save(user));
        }
        return userList;
    }

    private List<Category> seedCategories() {
        List<Category> catList = new ArrayList<>();
        String[] prefixes = {"SUB-6", "SUB-7", "SUB-8", "SUB-9", "SUB-10", "SUB-11", "SUB-12", "SUB-13", "SUB-14", "SUB-15", "SUB-16", "SUB-17", "SUB-18", "SUB-20"};
        String[] divisions = {"-A", "-B", "-C"};

        for (String p : prefixes) {
            for (String d : divisions) {
                if (catList.size() < 42) {
                    catList.add(categoryRepository.save(new Category(p + d)));
                }
            }
        }
        String[] specialCategories = {
            "FEMENINO-SUB-10", "FEMENINO-SUB-12", "FEMENINO-SUB-14", "FEMENINO-SUB-16", "FEMENINO-SUB-18",
            "INICIACION-A", "INICIACION-B", "ESCUELA-FORMATIVA", "ALTO-RENDIMIENTO-A", "ALTO-RENDIMIENTO-B"
        };
        for (String spec : specialCategories) {
            catList.add(categoryRepository.save(new Category(spec)));
        }
        return catList;
    }

    private List<Player> seedPlayers(List<Category> categories) {
        List<Player> playerList = new ArrayList<>();
        String[] names = {
            "Lionel", "Cristiano", "Kylian", "Erling", "Luka", "Kevin", "Andres", "Xavi", "Neymar", "Vinicius",
            "Jude", "Rodri", "Federico", "Ronald", "Sergio", "Thibaut", "Alisson", "Mohamed", "Bernardo", "Antoine"
        };
        String[] surnames = {
            "Messi", "Dos Santos", "Mbappe", "Haaland", "Modric", "De Bruyne", "Iniesta", "Hernandez", "Junior", "Valverde",
            "Bellingham", "Cascante", "Araujo", "Ramos", "Courtois", "Becker", "Salah", "Silva", "Griezmann", "Diaz"
        };

        for (int i = 1; i <= 55; i++) {
            String firstName = names[(i - 1) % names.length];
            String lastName = surnames[(i - 1) % surnames.length] + " " + (char) ('A' + (i % 26));
            LocalDate birthDate = LocalDate.of(2008 + (i % 9), (i % 12) + 1, (i % 28) + 1);
            LocalDate regDate = LocalDate.of(2022 + (i % 3), (i % 12) + 1, (i % 28) + 1);
            Category cat = categories.get((i - 1) % categories.size());

            Player p = new Player();
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setBirthDate(birthDate);
            p.setRegistrationDate(regDate);
            p.setCategory(cat);
            playerList.add(playerRepository.save(p));
        }
        return playerList;
    }

    private List<Guardian> seedGuardians() {
        List<Guardian> guardianList = new ArrayList<>();
        String[] guardianNames = {
            "Jorge Messi", "Dolores Aveiro", "Fayza Lamari", "Alf-Inge Haaland", "Stipe Modric",
            "Herwig De Bruyne", "Jose Antonio Iniesta", "Joaquim Hernandez", "Neymar Santos Sr", "Vinicius Paixao",
            "Mark Bellingham", "Antonio Cascante", "Julio Cesar Valverde", "Maria Gomez", "Fernando Ramos"
        };

        for (int i = 1; i <= 55; i++) {
            String name = guardianNames[(i - 1) % guardianNames.length] + " (" + i + ")";
            String phone = "+34 6" + String.format("%08d", 10000000 + i * 317);
            String email = "guardian." + i + "@familynetwork.org";

            Guardian g = new Guardian(name, phone, email);
            guardianList.add(guardianRepository.save(g));
        }
        return guardianList;
    }

    private void seedPlayerGuardians(List<Player> players, List<Guardian> guardians) {
        String[] relationships = {"Padre", "Madre", "Tutor Legal", "Tío", "Abuelo", "Hermano Mayor"};
        for (int i = 0; i < 55; i++) {
            Player p = players.get(i % players.size());
            Guardian g = guardians.get(i % guardians.size());
            String rel = relationships[i % relationships.length];

            PlayerGuardian pg = new PlayerGuardian(p, g, rel);
            playerGuardianRepository.save(pg);
        }
    }

    private void seedPlayerPositions(List<Player> players) {
        String[] posList = {
            "Portero", "Defensa Central", "Lateral Derecho", "Lateral Izquierdo",
            "Pivote Defensivo", "Mediocentro", "Mediapunta", "Extremo Derecho",
            "Extremo Izquierdo", "Delantero Centro"
        };

        for (int i = 0; i < 55; i++) {
            Player p = players.get(i);
            String pos1 = posList[i % posList.length];
            playerPositionRepository.save(new PlayerPosition(p, pos1));
        }
        // Add secondary positions for first 5 players to reach 60 rows
        for (int i = 0; i < 5; i++) {
            Player p = players.get(i);
            String pos2 = posList[(i + 3) % posList.length];
            playerPositionRepository.save(new PlayerPosition(p, pos2));
        }
    }

    private List<Tournament> seedTournaments() {
        List<Tournament> tournamentList = new ArrayList<>();
        String[] statuses = {"Activo", "Planificado", "Finalizado"};
        String[] names = {
            "Copa Apertura Oro", "Liga Juvenil Clausura", "Torneo Promesas del Futuro", "Copa Federacion Regional",
            "Liga de Campeones Infantil", "Copa Internacional Ciudad Real", "Torneo Primavera Soccer", "Copa Verano Elite",
            "Supercopa Academias", "Torneo Otoño Interclubes", "Copa Navidad Talentos", "Liga Formativa Metropolitana",
            "Torneo Relampago Sub-12"
        };

        for (int i = 1; i <= 52; i++) {
            String tName = names[(i - 1) % names.length] + " Edición " + (2020 + i);
            LocalDate start = LocalDate.of(2025, ((i - 1) % 12) + 1, 1).plusDays((i * 2) % 20);
            LocalDate end = start.plusMonths(2);
            String status = statuses[(i - 1) % statuses.length];

            Tournament t = new Tournament(tName, start, end, status);
            tournamentList.add(tournamentRepository.save(t));
        }
        return tournamentList;
    }

    private void seedTournamentMatchdays(List<Tournament> tournaments) {
        String[] homeTeams = {"Academia FC Sub-12", "Real Balompié", "Los Halcones FC", "Deportivo Cantera", "Sporting Metropolitano", "Estrella del Este"};
        String[] awayTeams = {"Atlético Promesas", "Huracán Juvenil", "Rayos Soccer Club", "Alianza Futuro", "CD Juventud Unida", "Centauros FC"};
        String[] locations = {
            "Sede Cancha Principal - Campo #1", "Sede Cancha Principal - Campo #2",
            "Polideportivo Municipal - Pista Norte", "Complejo Deportivo El Retiro - Campo A",
            "Estadio de la Juventud - Cancha Sintética #3"
        };

        for (int i = 1; i <= 55; i++) {
            Tournament t = tournaments.get((i - 1) % tournaments.size());
            String home = homeTeams[(i - 1) % homeTeams.length];
            String away = awayTeams[(i - 1) % awayTeams.length];
            LocalDateTime dt = LocalDateTime.of(2025, ((i - 1) % 12) + 1, (i % 25) + 1, 10 + (i % 8), (i % 2 == 0) ? 0 : 30);
            String loc = locations[(i - 1) % locations.length];

            TournamentMatchday m = new TournamentMatchday(null, t, home, away, dt, loc);
            tournamentMatchdayRepository.save(m);
        }
    }

    private List<PaymentConcept> seedPaymentConcepts() {
        List<PaymentConcept> concepts = new ArrayList<>();
        String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        // Mensualidades across categories
        for (String m : months) {
            concepts.add(paymentConceptRepository.save(new PaymentConcept("Mensualidad " + m + " - Sub-10", new BigDecimal("55.00"))));
            concepts.add(paymentConceptRepository.save(new PaymentConcept("Mensualidad " + m + " - Sub-12", new BigDecimal("60.00"))));
            concepts.add(paymentConceptRepository.save(new PaymentConcept("Mensualidad " + m + " - Sub-14", new BigDecimal("65.00"))));
        }

        // Special fees
        String[] extraConcepts = {
            "Matrícula e Inscripción Anual 2025", "Pack Uniforme Oficial (Local/Visitante)",
            "Chaqueta y Ropa de Abrigo Oficial", "Seguro Médico Deportivo Anual",
            "Cuota de Arbitraje Torneo Apertura", "Cuota de Arbitraje Torneo Clausura",
            "Transporte Oficial para Torneos Fuera de Sede", "Campamento Deportivo Verano 2025",
            "Curso de Tecnificación de Alto Rendimiento", "Evaluación Fisioterapéutica Especial",
            "Certificación de Ficha Federativa", "Kit de Entrenamiento y Mochila Oficial",
            "Bono Nutricional para Jugadores Elite", "Carnet de Socio y Acceso a Instalaciones",
            "Derechos de Participación Copa de Oro", "Taller de Psicología Deportiva"
        };

        for (String ec : extraConcepts) {
            if (concepts.size() < 52) {
                concepts.add(paymentConceptRepository.save(new PaymentConcept(ec, new BigDecimal("120.00"))));
            }
        }
        return concepts;
    }

    private void seedPayments(List<Player> players, List<PaymentConcept> concepts, List<User> users) {
        String[] statuses = {"Aprobado", "Pendiente", "Rechazado"};
        for (int i = 1; i <= 55; i++) {
            Player p = players.get((i - 1) % players.size());
            PaymentConcept c = concepts.get((i - 1) % concepts.size());
            LocalDateTime reportDate = LocalDateTime.of(2025, ((i - 1) % 12) + 1, (i % 27) + 1, 9 + (i % 10), (i * 7) % 60);
            BigDecimal amount = c.getExpectedAmount();
            String bankRef = "BANCO-REF-" + String.format("%08d", 80000000 + i * 491);
            String status = statuses[(i - 1) % statuses.length];
            User validator = "Aprobado".equals(status) ? users.get((i - 1) % users.size()) : null;

            Payment payment = new Payment(null, p, c, reportDate, amount, bankRef, status, validator);
            paymentRepository.save(payment);
        }
    }

    private void seedNews(List<User> users) {
        String[] titles = {
            "Gran inauguración de la temporada 2025",
            "Victoria contundente de nuestra categoría Sub-12 en la final",
            "Convocatoria oficial para las pruebas de captación juvenil",
            "Jornada de convivencia y valores deportivos con las familias",
            "Presentación de la nueva indumentaria deportiva oficial",
            "Firma de acuerdo de colaboración para becas deportivas",
            "Recomendaciones nutricionales para el alto rendimiento",
            "Horarios y sedes confirmadas para la próxima jornada liguera",
            "Entrevista exclusiva con el director de la cantera",
            "Nuestro equipo Sub-14 clasifica al torneo internacional",
            "Nuevo protocolo de preparación física y prevención de lesiones",
            "Resumen estadístico del mes y jugadores más destacados",
            "Renovación de instalaciones y césped en el campo principal"
        };

        for (int i = 1; i <= 52; i++) {
            String title = titles[(i - 1) % titles.length] + " - Boletín #" + i;
            String content = "Detalle informativo sobre la actualidad del club correspondiente a la entrega número " + i +
                    ". Nuestros jugadores y cuerpo técnico siguen demostrando el máximo compromiso, disciplina deportiva y espíritu de superación en cada competición.";
            String imgUrl = "https://images.unsplash.com/photo-1508098682722-e99c43a406b2?auto=format&fit=crop&w=800&q=80";
            LocalDateTime pubDate = LocalDateTime.of(2025, ((i - 1) % 12) + 1, (i % 27) + 1, 8 + (i % 12), 0);
            User author = users.get((i - 1) % users.size());

            News news = new News(title, content, imgUrl, pubDate, author);
            newsRepository.save(news);
        }
    }

    private void logTableCounts() {
        log.info("================ RECORD COUNT VERIFICATION ================");
        log.info("1. roles:               {} records", roleRepository.count());
        log.info("2. usuarios:            {} records", userRepository.count());
        log.info("3. categorias:          {} records", categoryRepository.count());
        log.info("4. jugadores:           {} records", playerRepository.count());
        log.info("5. tutores:             {} records", guardianRepository.count());
        log.info("6. jugadores_tutores:   {} records", playerGuardianRepository.count());
        log.info("7. jugador_posiciones:  {} records", playerPositionRepository.count());
        log.info("8. torneos:             {} records", tournamentRepository.count());
        log.info("9. torneo_jornadas:     {} records", tournamentMatchdayRepository.count());
        log.info("10. conceptos_pago:     {} records", paymentConceptRepository.count());
        log.info("11. pagos:              {} records", paymentRepository.count());
        log.info("12. noticias:           {} records", newsRepository.count());
        log.info("============================================================");
    }
}
