package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record PaymentConceptDto(
    Long id,
    @NotBlank(message = "Concept name is required")
    @Size(max = 120, message = "Concept name must not exceed 120 characters")
    String conceptName,

    @NotNull(message = "Expected amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Expected amount must be greater than zero")
    BigDecimal expectedAmount
) {}
