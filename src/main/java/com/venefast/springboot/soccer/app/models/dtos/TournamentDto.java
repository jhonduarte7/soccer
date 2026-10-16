package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TournamentDto(
    Long id,
    @NotBlank(message = "Tournament name is required")
    @Size(max = 120, message = "Tournament name must not exceed 120 characters")
    String tournamentName,

    @NotNull(message = "Start date is required")
    LocalDate startDate,

    @NotNull(message = "End date is required")
    LocalDate endDate,

    @NotBlank(message = "Status is required")
    @Size(max = 40, message = "Status must not exceed 40 characters")
    String status
) {}
