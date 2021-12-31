package com.jobify.microservices.services;

import com.jobify.microservices.entities.dtos.UserDto;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<UserDto> signup(UserDto userDto);
}
