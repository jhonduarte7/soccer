package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto(
    Long id,
    @NotNull(message = "Player ID is required")
    Long playerId,

    String playerName,

    @NotNull(message = "Payment concept ID is required")
    Long paymentConceptId,

    String paymentConceptName,

    @NotNull(message = "Report date is required")
    LocalDateTime reportDate,

    @NotNull(message = "Amount paid is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount paid must be greater than zero")
    BigDecimal amountPaid,

    @NotBlank(message = "Bank reference is required")
    @Size(max = 100, message = "Bank reference must not exceed 100 characters")
    String bankReference,

    @NotBlank(message = "Payment status is required")
    @Size(max = 40, message = "Payment status must not exceed 40 characters")
    String paymentStatus,

    Long validatorUserId,
    String validatorUsername
) {}
