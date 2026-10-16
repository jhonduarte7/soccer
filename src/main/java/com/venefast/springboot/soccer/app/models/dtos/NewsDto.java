package com.venefast.springboot.soccer.app.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record NewsDto(
    Long id,
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    String title,

    @NotBlank(message = "Content is required")
    String content,

    @Size(max = 300, message = "Image URL must not exceed 300 characters")
    String imageUrl,

    LocalDateTime publicationDate,

    @NotNull(message = "Author ID is required")
    Long authorId,

    String authorUsername
) {}
