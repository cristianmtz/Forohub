package com.aluracursos.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto(


        @NotBlank
        String username,

        @NotBlank
        String password

) {
}
