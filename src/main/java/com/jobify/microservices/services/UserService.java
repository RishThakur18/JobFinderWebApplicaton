package com.jobify.microservices.services;

import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.entities.dtos.UserDto;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<UserDto> viewAll();
    Mono<UserDto> viewUser(String id);
    Mono<UserDto> updateUser(String id, UserDto userDto);
    Mono<String> deleteUser(String id);
}
