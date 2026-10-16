# AGENTS.md - Workspace Standards, Execution & Active Skills

## 1. Overview
This workspace is configured for developing high-quality Spring Boot applications following industry best practices and strict layered architecture.
- **Project Name**: Soccer Management API (`soccer`)
- **Technology Stack**: Java 21+ (Java 25 compatible runtime), Spring Boot 3.4.3, Spring Data JPA, Hibernate, Bean Validation, H2 In-Memory Database.
- **Default Port**: `8082`
- **Remote Git Repository**: `https://github.com/jhonduarte7/SOCCER`

---

## 2. Active Skills
- **`spring-boot-best-practices-workspaces`**:
  - Source: `.agents/skills/spring-boot-best-practices-workspaces/SKILL.md`
  - Scope: Workspace governance, code language (English), user communication language (Spanish), Java compliance, and skill orchestration.
- **`spring-boot-best-practices`**:
  - Source: `.agents/skills/spring-boot-best-practices/SKILL.md`
  - Scope: Architectural layers (Controllers, Services, Repositories, Entities, DTOs with `record`, Mappers with `@Component`), Maven wrapper standards, and H2 in-memory persistence.
- **`skill-creator`**:
  - Source: `.agents/skills/skill-creator/SKILL.md`
  - Scope: Creating, modifying, evaluating, and maintaining agent skills.

---

## 3. Architecture Rules
- **Package Structure**: `com.venefast.springboot.soccer.app`
  - `controllers/`: Exposes REST endpoints (`/api/...`), validates requests using Bean Validation (`@Valid`), delegates to services, returns `ResponseEntity<DTO>`.
  - `services/`: Encapsulates business logic and transactional boundaries (`@Transactional`). Injects repositories and mappers. Receives and returns DTOs.
  - `repositories/`: Spring Data JPA interfaces extending `JpaRepository`.
  - `models/entities/`: JPA Entities mapping relational tables (including 4NF normal form relationships).
  - `models/dtos/`: Java `record` classes representing immutable data contracts.
  - `mappers/`: Spring `@Component` beans performing bidirectional conversions between Entities and DTOs.
  - `config/`: Application configuration and data initializers (`DataLoader`).
  - `exceptions/`: Centralized REST error handling with `@RestControllerAdvice`.

---

## 4. Database & Mock Data
- **Database**: H2 In-Memory (`jdbc:h2:mem:soccerdb`).
- **H2 Console**: Available at `http://localhost:8082/h2-console`
  - **Driver Class**: `org.h2.Driver`
  - **JDBC URL**: `jdbc:h2:mem:soccerdb`
  - **User**: `sa`
  - **Password**: *(leave blank)*
- **Mock Data**: Seeded automatically at startup via `DataLoader` with `>= 50` records across all 12 tables:
  1. `roles` (52 records)
  2. `usuarios` (55 records)
  3. `categorias` (52 records)
  4. `jugadores` (55 records)
  5. `tutores` (55 records)
  6. `jugadores_tutores` (55 records)
  7. `jugador_posiciones` (60 records)
  8. `torneos` (52 records)
  9. `torneo_jornadas` (55 records)
  10. `conceptos_pago` (52 records)
  11. `pagos` (55 records)
  12. `noticias` (52 records)

---

## 5. Commands to Run, Test, and Build the Backend

### Windows (PowerShell / CMD)
```powershell
# 1. Run the Spring Boot application
.\mvnw.cmd spring-boot:run

# 2. Run with custom port (e.g., 8080)
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--server.port=8080"

# 3. Clean and run tests
.\mvnw.cmd test

# 4. Package executable JAR (skipping tests if needed)
.\mvnw.cmd clean package -DskipTests
```

### Linux / macOS (Bash)
```bash
# 1. Make wrapper executable (first time only)
chmod +x mvnw

# 2. Run the application
./mvnw spring-boot:run

# 3. Run unit & integration tests
./mvnw test

# 4. Package JAR
./mvnw clean package
```

### Executing the Packaged JAR
```powershell
java -jar target/soccer-0.0.1-SNAPSHOT.jar
```

---

## 6. Endpoints & Health Check
- **Actuator Health**: `GET http://localhost:8082/actuator/health`
- **Database Verification & Stats**: `GET http://localhost:8082/api/stats`
- **Main REST API Endpoints**:
  - `/api/roles`
  - `/api/users`
  - `/api/categories`
  - `/api/players`
  - `/api/guardians`
  - `/api/player-guardians`
  - `/api/player-positions`
  - `/api/tournaments`
  - `/api/tournament-matchdays`
  - `/api/payment-concepts`
  - `/api/payments`
  - `/api/news`
