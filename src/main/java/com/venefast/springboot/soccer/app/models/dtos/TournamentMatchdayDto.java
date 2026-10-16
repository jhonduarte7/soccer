package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record TournamentMatchdayDto(
    Long id,
    @NotNull(message = "Tournament ID is required")
    Long tournamentId,

    String tournamentName,

    @NotBlank(message = "Home team is required")
    @Size(max = 100, message = "Home team must not exceed 100 characters")
    String homeTeam,

    @NotBlank(message = "Away team is required")
    @Size(max = 100, message = "Away team must not exceed 100 characters")
    String awayTeam,

    @NotNull(message = "Match date and time is required")
    LocalDateTime matchDateTime,

    @NotBlank(message = "Location is required")
    @Size(max = 150, message = "Location must not exceed 150 characters")
    String location
) {}
