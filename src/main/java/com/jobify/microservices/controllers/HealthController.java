package com.jobify.microservices.controllers;

import com.jobify.microservices.entities.dtos.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping()
    public Mono<ResponseDto> health(){
        return Mono.just(ResponseDto.builder()
                .message("Application is up and running successfully!")
                .build());
    }
}
