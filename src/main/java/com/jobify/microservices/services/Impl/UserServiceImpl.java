package com.jobify.microservices.services.Impl;

import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.entities.mappers.UserMapper;
import com.jobify.microservices.repositories.UserRepo;
import com.jobify.microservices.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public Flux<UserDto> viewAll(){
        return userRepo
                .findAll()
                .map(UserMapper.INSTANCE::modelToDto)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    public Mono<UserDto> viewUser(String id){
        return userRepo.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found")))
                .map(UserMapper.INSTANCE::modelToDto);
    }

    public Mono<String> deleteUser(String id){
        return userRepo.deleteById(id)
                .then(Mono.just("User with id "+ id +" deleted successfully"));
    }

    public Mono<UserDto> updateUser(String id,UserDto userDto){
        return userRepo.findById(id)
                .flatMap(user -> Mono.just(userDto))
                .map(UserMapper.INSTANCE::dtoToModel)
                .doOnNext(user -> user.setId(id))
                .flatMap(userRepo::save)
                .map(UserMapper.INSTANCE::modelToDto);
    }
}
