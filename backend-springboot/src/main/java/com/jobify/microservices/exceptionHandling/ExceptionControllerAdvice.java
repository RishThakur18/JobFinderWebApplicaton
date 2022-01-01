package com.jobify.microservices.exceptionHandling;

import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.exceptionHandling.customExceptions.UserAlreadyExistsException;
import com.jobify.microservices.exceptionHandling.customExceptions.UserNotFoundException;
import com.jobify.microservices.exceptionHandling.customExceptions.WrongCredentialsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
import javax.validation.ConstraintViolationException;

@Log4j2
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionControllerAdvice { ;
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseDto> constraintViolationException(final Exception e){
        log.error("constraintViolationException : {} ",e.getMessage());
        return Mono.just(ResponseDto
                .builder()
                .reason(ExceptionsList.ConstraintViolationException.getReason())
                .exception(ConstraintViolationException.class.getName())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Mono<ResponseDto> userNotFoundException(final Exception e){
        log.error("userNotFoundException : {} ",e.getMessage());
        return Mono.just(ResponseDto
                .builder()
                .reason(ExceptionsList.UserNotFoundException.getReason())
                .exception(UserNotFoundException.class.getName())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Mono<ResponseDto> userAlreadyExistsException(final Exception e){
        log.error("userAlreadyExistsException : {} ",e.getMessage());
        return Mono.just(ResponseDto
                .builder()
                .reason(ExceptionsList.UserAlreadyExistsException.getReason())
                .exception(WrongCredentialsException.class.getName())
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(value = WrongCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mono<ResponseDto> wrongCredentialsException(final Exception e){
        log.error("wrongCredentialsException : {} ",e.getMessage());
        return Mono.just(ResponseDto
                .builder()
                .reason(ExceptionsList.WrongCredentialsException.getReason())
                .exception(WrongCredentialsException.class.getName())
                .message(e.getMessage())
                .build());
    }
}


