package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GuardianDto(
    Long id,
    @NotBlank(message = "Full name is required")
    @Size(max = 150, message = "Full name must not exceed 150 characters")
    String fullName,

    @NotBlank(message = "Phone number is required")
    @Size(max = 30, message = "Phone must not exceed 30 characters")
    String phone,

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 120, message = "Email must not exceed 120 characters")
    String email
) {}
