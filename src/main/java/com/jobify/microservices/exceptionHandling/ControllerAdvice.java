package com.jobify.microservices.exceptionHandling;

import com.jobify.microservices.entities.dtos.ResponseDto;
import com.jobify.microservices.entities.enums.CustomException;
import com.jobify.microservices.exceptionHandling.customExceptions.*;
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
    public Mono<ResponseDto> bad_request(final Exception e){
        return errorResponseBuilder(e);
    }


    @ExceptionHandler(value = {
            UserNotFoundException.class,
            UserAlreadyExistsException.class
    })
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Mono<ResponseDto> not_acceptable(final Exception e){
        return errorResponseBuilder(e);
    }


    @ExceptionHandler(value = {
            WrongCredentialsException.class,
            AuthorizationException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mono<ResponseDto> unauthorized(final Exception e) {
        return errorResponseBuilder(e);
    }

    @ExceptionHandler(value = {
            MailNotSentException.class,
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResponseDto> internal_server_error(final Exception e) {
        return errorResponseBuilder(e);
    }



    private static Mono<ResponseDto> errorResponseBuilder(Exception e){
        return Mono.just(ResponseDto
                        .builder()
                        .message(CustomException.valueOf(e.getClass().getSimpleName()).getMessage())
                        .exception(e.getClass().getName())
                        .reason(e.getMessage())
                        .build())
                .doOnNext(exceptionInfo ->  log.error("Exception : {} ",exceptionInfo));
    }
}


