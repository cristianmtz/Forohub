package com.aluracursos.forohub.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class AuthenticationResponse {

    private String token;

    // Constructor sin argumentos
    public AuthenticationResponse() {}

    // Constructor con todos los argumentos
    public AuthenticationResponse(String token) {
        this.token = token;
    }
}
