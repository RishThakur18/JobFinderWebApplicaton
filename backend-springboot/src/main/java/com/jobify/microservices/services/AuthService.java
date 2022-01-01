package com.jobify.microservices.services;

import com.jobify.microservices.entities.dtos.LoginRequestDto;
import com.jobify.microservices.entities.dtos.UserDto;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
public interface AuthService {
    Mono<UserDto> signup(@Valid  UserDto userDto);
    Mono<String> login(@Valid LoginRequestDto loginRequestDto);
}
