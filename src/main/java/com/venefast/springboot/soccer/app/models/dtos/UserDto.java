package com.venefast.springboot.soccer.app.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(
    Long id,
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 60, message = "Username must be between 3 and 60 characters")
    String username,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password,

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 120, message = "Email must not exceed 120 characters")
    String email,

    @NotNull(message = "Role ID is required")
    Long roleId,

    String roleName
) {}
