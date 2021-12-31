package com.jobify.microservices.services.Impl;

import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.entities.mappers.UserMapper;
import com.jobify.microservices.repositories.UserRepo;
import com.jobify.microservices.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;

    @Override
    public Mono<UserDto> signup(UserDto userDto) {
        return Mono.just(userDto)
                .map(UserMapper.INSTANCE::dtoToModel)
                .flatMap(userRepo::save)
                .map(UserMapper.INSTANCE::modelToDto);
    }
}
