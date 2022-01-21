package com.jobify.microservices.services;

import com.jobify.microservices.entities.dtos.LoginRequestDto;
import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.entities.models.User;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
public interface AuthService  {

    Mono<Boolean> sendVerificationEmail(@Valid String email);
    Mono<Boolean> verifyMail(@Valid String string, @Valid String otp);
    Mono<String> login(@Valid LoginRequestDto loginRequestDto);
    Mono<UserDto> signup(UserDto userDto);
}

