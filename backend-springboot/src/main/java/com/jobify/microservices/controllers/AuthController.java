package com.jobify.microservices.controllers;

import com.jobify.microservices.entities.dtos.LoginRequestDto;
import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping(value ="/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseDto> signUp(@RequestBody UserDto userDto) {
        return authService.signup(userDto)
                .map(data -> ResponseDto
                        .builder()
                        .message("Signup Successful")
                        .data(data)
                        .build());
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseDto> logIn(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto)
                .map(token -> ResponseDto
                        .builder()
                        .message("Signup Successful")
                        .token(token)
                        .build());
    }
}
