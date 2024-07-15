package com.aluracursos.forohub.controller;



import com.aluracursos.forohub.dto.UserDto;
import com.aluracursos.forohub.model.AuthenticationResponse;
import com.aluracursos.forohub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;



    @Operation(
            summary = "Register a new user",
            description = "Allows the user to register a new account."
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.register(userDto));
    }


    @Operation(
            summary = "Authenticate user",
            description = "Allows the user to authenticate with their credentials."
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.authenticate(userDto));
    }



}
