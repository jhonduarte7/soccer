package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlayerPositionDto(
    @NotNull(message = "Player ID is required")
    Long playerId,

    String playerName,

    @NotBlank(message = "Position is required")
    @Size(max = 50, message = "Position must not exceed 50 characters")
    String position
) {}
