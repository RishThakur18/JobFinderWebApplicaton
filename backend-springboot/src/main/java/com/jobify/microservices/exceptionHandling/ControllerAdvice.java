package com.jobify.microservices.exceptionHandling;

import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.exceptionHandling.customExceptions.AuthorizationException;
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
public class ControllerAdvice {

    @ExceptionHandler(value = {
            ConstraintViolationException.class
            })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ResponseDto> constraintViolationException(final Exception e){
        return this.errorResponseBuilder(e);
    }


    @ExceptionHandler(value = {
            UserNotFoundException.class,
            UserAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Mono<ResponseDto> userNotFoundException(final Exception e){
        return this.errorResponseBuilder(e);
    }


    @ExceptionHandler(value = {
            WrongCredentialsException.class,
            AuthorizationException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mono<ResponseDto> responseStatusUnauthorized(final Exception e) {
        return this.errorResponseBuilder(e);
    }


    private Mono<ResponseDto> errorResponseBuilder(Exception e){
        return Mono.just(ResponseDto
                        .builder()
                        .message(ExceptionsList.valueOf(e.getClass().getSimpleName()).getMessage())
                        .exception(e.getClass().getName())
                        .reason(e.getMessage())
                        .build())
                .doOnNext(exceptionInfo ->  log.error("Exception : {} ",exceptionInfo));
    }
}


