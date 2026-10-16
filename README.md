# ⚽ Soccer Management API

API RESTful empresarial desarrollada con **Spring Boot 3.4.3** y **Java 21/25** para la gestión integral de academias, clubes y torneos de fútbol. Implementa una arquitectura en capas limpia y desacoplada con Spring Data JPA, Hibernate, Bean Validation, Java Records, Mappers basados en Spring Components y persistencia en memoria con base de datos H2.

---

## 🚀 Características Principales

- **Arquitectura en Capas Estricta**: Controladores, Servicios Transaccionales, Repositorios JPA, Entidades Normalizadas (4FN), DTOs inmutables con `record` y Mappers independientes.
- **Gestión Completa de Entidades**:
  - **Usuarios y Roles**: Autenticación, perfiles y control de acceso.
  - **Categorías**: Clasificación de jugadores por rangos de edad.
  - **Jugadores y Tutores**: Registro de deportistas y asignación de representantes/tutores legales con relaciones complejas.
  - **Posiciones de Jugador**: Historial y mapeo de posiciones de campo por jugador.
  - **Torneos y Jornadas**: Planificación de calendarios deportivos y fechas de competencia.
  - **Finanzas**: Gestión de conceptos de cobro y registro detallado de pagos.
  - **Noticias**: Publicación de avisos y novedades institucionales.
- **Base de Datos H2 In-Memory**: Cero dependencias externas para desarrollo y pruebas rápidas.
- **Semillado Automático de Datos**: Carga inicial automática de más de 50 registros por cada una de las 12 tablas al arrancar la aplicación (`DataLoader`).
- **Verificación y Monitoreo**: Endpoint de estadísticas de base de datos (`/api/stats`) y Spring Boot Actuator (`/actuator/health`).

---

## 🛠️ Tecnologías Utilizadas

- **Java**: 21 LTS (compatible con entornos de ejecución Java 25)
- **Framework**: Spring Boot 3.4.3
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-validation`
  - `spring-boot-starter-actuator`
  - `spring-boot-devtools`
- **Base de Datos**: H2 Database Engine (In-Memory)
- **Persistencia**: Hibernate ORM 6.6+
- **Construcción y Dependencias**: Maven Wrapper (`mvnw`)

---

## 📋 Requisitos Previos

- **Java Development Kit (JDK)**: Versión 21 o superior instalada y configurada en el `PATH` (`JAVA_HOME`).
- **Git**: Para control de versiones y clonación del repositorio.
- No se requiere tener Maven instalado globalmente, el proyecto incluye el ejecutable Maven Wrapper (`mvnw` / `mvnw.cmd`).

---

## ⚙️ Comandos para Correr e Iniciar el Backend

### 1. En Windows (PowerShell o Símbolo del Sistema)

Para iniciar el servidor en modo desarrollo:
```powershell
.\mvnw.cmd spring-boot:run
```

Para compilar y ejecutar las pruebas unitarias y de integración:
```powershell
.\mvnw.cmd test
```

Para empaquetar el proyecto en un archivo JAR ejecutable:
```powershell
.\mvnw.cmd clean package
```

Para ejecutar el archivo JAR generado:
```powershell
java -jar target/soccer-0.0.1-SNAPSHOT.jar
```

---

### 2. En Linux / macOS (Bash o Zsh)

Dar permisos de ejecución al script envoltorio (solo la primera vez):
```bash
chmod +x mvnw
```

Iniciar el servidor Spring Boot:
```bash
./mvnw spring-boot:run
```

Ejecutar pruebas:
```bash
./mvnw test
```

Empaquetar la aplicación:
```bash
./mvnw clean package
```

Ejecutar el JAR:
```bash
java -jar target/soccer-0.0.1-SNAPSHOT.jar
```

---

### 3. Configuración de Puerto Alternativo

Por defecto, la API corre en el puerto **`8082`**. Si necesitas iniciarla en otro puerto (ejemplo: `8080`), puedes pasar el argumento por línea de comandos:

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--server.port=8080"
```

---

## 🗄️ Acceso a la Consola de Base de Datos H2

La base de datos H2 está disponible mientras la aplicación esté en ejecución:

- **URL de la Consola**: [http://localhost:8082/h2-console](http://localhost:8082/h2-console)
- **JDBC Driver**: `org.h2.Driver`
- **JDBC URL**: `jdbc:h2:mem:soccerdb`
- **User Name**: `sa`
- **Password**: *(dejar en blanco)*

---

## 📊 Endpoints de Verificación y Salud

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `http://localhost:8082/actuator/health` | Estado de salud general del servicio Spring Boot |
| `GET` | `http://localhost:8082/actuator/info` | Información general de la aplicación |
| `GET` | `http://localhost:8082/api/stats` | Conteo en vivo de registros por tabla y verificación de objetivo (>= 50 registros) |

---

## 🌐 Catálogo de Recursos REST API

Todos los endpoints REST admiten operaciones CRUD completas:

| Recurso | Ruta Base | Descripción |
|---|---|---|
| **Roles** | `/api/roles` | Gestión de perfiles y roles del sistema |
| **Usuarios** | `/api/users` | Gestión de cuentas de usuario y credenciales |
| **Categorías** | `/api/categories` | Divisiones y categorías deportivas |
| **Jugadores** | `/api/players` | Ficha técnica y registro de jugadores |
| **Tutores** | `/api/guardians` | Registro de representantes legales y contacto |
| **Jugador - Tutor** | `/api/player-guardians` | Asignación y parentesco entre jugador y tutor |
| **Posiciones** | `/api/player-positions` | Posiciones tácticas de juego por jugador |
| **Torneos** | `/api/tournaments` | Campeonatos, ligas y torneos |
| **Jornadas** | `/api/tournament-matchdays` | Calendario de fechas y jornadas de torneo |
| **Conceptos de Pago** | `/api/payment-concepts` | Catálogo de aranceles (inscripción, cuotas, uniformes) |
| **Pagos** | `/api/payments` | Transacciones y comprobantes de pago de jugadores |
| **Noticias** | `/api/news` | Tablón de noticias y comunicados |

### Ejemplos de Peticiones

#### Listar todos los jugadores:
```bash
curl -X GET http://localhost:8082/api/players
```

#### Obtener estadísticas de registros:
```bash
curl -X GET http://localhost:8082/api/stats
```

#### Crear un nuevo rol:
```bash
curl -X POST http://localhost:8082/api/roles \
  -H "Content-Type: application/json" \
  -d '{"name": "DIRECTOR_TECNICO", "description": "Director técnico principal"}'
```

---

## 📂 Estructura del Proyecto

```
SOCCER/
├── .agents/                               # Reglas y habilidades activas de los agentes
├── .mvn/wrapper/                          # Binarios y propiedades del Maven Wrapper
├── src/
│   ├── main/
│   │   ├── java/com/venefast/springboot/soccer/app/
│   │   │   ├── config/                    # Configuración inicial y DataLoader
│   │   │   ├── controllers/               # Controladores REST (/api/...)
│   │   │   ├── exceptions/                # Manejo global de excepciones (@RestControllerAdvice)
│   │   │   ├── mappers/                   # Componentes de mapeo Bidireccional Entity <-> DTO
│   │   │   ├── models/
│   │   │   │   ├── dtos/                  # Java Records inmutables
│   │   │   │   └── entities/              # Entidades JPA relacionales
│   │   │   ├── repositories/              # Interfaces Spring Data JPA
│   │   │   ├── services/                  # Servicios de lógica de negocio transaccionales
│   │   │   └── SoccerApplication.java     # Clase principal de arranque
│   │   └── resources/
│   │       └── application.properties     # Configuración de base de datos, puerto y consola H2
│   └── test/                              # Pruebas unitarias y de integración
├── AGENTS.md                              # Reglas del espacio de trabajo y contexto del backend
├── pom.xml                                # Descriptor Maven y dependencias del proyecto
├── mvnw / mvnw.cmd                        # Scripts de ejecución Maven Wrapper
└── README.md                              # Documentación del proyecto
```

---

## 📌 Repositorio

- **GitHub Repository**: [https://github.com/jhonduarte7/soccer](https://github.com/jhonduarte7/soccer)
- **Autor / Propietario**: `jhonduarte7`
