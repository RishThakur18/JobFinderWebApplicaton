package com.jobify.microservices.controllers;

import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/viewAllUsers")
    public Flux<ResponseDto> viewAll(){
        return userService.viewAll()
                .map(userList -> ResponseDto.builder()
                        .data(userList)
                        .build());
    }

    @GetMapping("/{id}")
    public Mono<ResponseDto> view(@PathVariable String id){
        return userService.viewUser(id)
                .map(user -> ResponseDto.builder()
                .data(user)
                .build());
    }

    @PutMapping("/{id}/update")
    public Mono<ResponseDto> updateUserInfo(@PathVariable String id, @RequestBody UserDto userDto){
        return userService.updateUser(id,userDto)
                .map(user -> ResponseDto.builder()
                        .data(user)
                        .build());
    }

    @DeleteMapping("/{id}/delete")
    public Mono<ResponseDto> removeUser(@PathVariable String id){
        return userService.deleteUser(id)
                .map(message -> ResponseDto.builder()
                        .message(message)
                        .build());
    }
}
