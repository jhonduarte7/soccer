package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleDto(
    Long id,
    @NotBlank(message = "Role name is required")
    @Size(max = 80, message = "Role name must not exceed 80 characters")
    String name
) {}
