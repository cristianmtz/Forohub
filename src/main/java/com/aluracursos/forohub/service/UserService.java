package com.aluracursos.forohub.service;


import com.aluracursos.forohub.dto.UserDto;
import com.aluracursos.forohub.mapper.UserMapperInterface;
import com.aluracursos.forohub.model.AuthenticationResponse;
import com.aluracursos.forohub.model.Role;
import com.aluracursos.forohub.model.User;
import com.aluracursos.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserMapperInterface userMapperInterface;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(UserDto userDto){

        var user = User.builder()
                .username(userDto.username())
                .password(passwordEncoder.encode(userDto.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(UserDto userDto){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.username(),
                        userDto.password()
                )
        );
        var user = userRepository.findByUsername(userDto.username())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    @Transactional
    public void saveUser(UserDto userDto){

        //Mapping from UserDto to User
        User user = userMapperInterface.toEntity(userDto);

        //Save in DataBase
        userRepository.save(user);
    }

    @Transactional
    public Optional<User> findByUsername(String username){
        return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Not username found")));

    }



}
