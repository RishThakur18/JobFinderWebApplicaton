package com.jobify.microservices.controllers;

import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.exceptions.InvalidObjectException;
import com.jobify.microservices.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
@AllArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping(value ="/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseDto> signUp(@RequestBody @Valid UserDto userDto )
            throws InvalidObjectException {
        return authService.signup(userDto)
//                .switchIfEmpty(Mono.error())
                .map(data -> ResponseDto
                        .builder()
                        .message("Signup Successful")
                        .data(data)
                        .build());
    }
}
