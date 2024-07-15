package com.aluracursos.forohub.dto;


import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


public record PostDto(

    @NotBlank
    String title,

    @NotBlank
    String content,

    LocalDateTime createdAt


) {
}
