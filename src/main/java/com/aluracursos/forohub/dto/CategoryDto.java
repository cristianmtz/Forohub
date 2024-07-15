package com.aluracursos.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryDto(


        @NotBlank
        String name,

         @NotBlank
         String description

) {
}
