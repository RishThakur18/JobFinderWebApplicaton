package com.jobify.microservices.controllers;

import com.jobify.microservices.entities.dtos.LoginRequestDto;
import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.entities.dtos.UserDto;
import com.jobify.microservices.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;


    @GetMapping(value ="/verify/{email}")
    public Mono<ResponseDto> registerMail(@PathVariable(name="email")final String email) {
        return authService.sendVerificationEmail(email)
                .map(data -> ResponseDto
                        .builder()
                        .message("verification mail sent successfully")
                        .build());
    }

    @GetMapping(value ="/verify/{email}/{otp}")
    public Mono<ResponseDto> verifyMail(@PathVariable(name="email") final String email, @RequestParam(name="otp")final String otp) {
        return authService.verifyMail(email,otp)
                .map(data -> ResponseDto
                        .builder()
                        .message("Signup Successful")
                        .data(data)
                        .build());
    }

    @PostMapping(value ="/signup")
    public Mono<ResponseDto> signUp(@RequestBody final UserDto userDto) {
        return authService.signup(userDto)
                .map(data -> ResponseDto
                        .builder()
                        .message("Signup Successful")
                        .data(data)
                        .build());
    }

    @PostMapping(value = "/login")
    public Mono<ResponseDto> logIn(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto)
                .map(token -> ResponseDto
                        .builder()
                        .message("Signup Successful")
                        .token(token)
                        .build());
    }
}
