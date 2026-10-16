package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlayerGuardianDto(
    @NotNull(message = "Player ID is required")
    Long playerId,

    String playerName,

    @NotNull(message = "Guardian ID is required")
    Long guardianId,

    String guardianName,

    @NotBlank(message = "Relationship is required")
    @Size(max = 50, message = "Relationship must not exceed 50 characters")
    String relationship
) {}
