package com.jobify.microservices.exceptions;

import com.jobify.microservices.entities.enums.ExceptionsList;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = InvalidObjectException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionsList invalidObjectException(final Exception e) {
        log.error("invalidObjectException -> {}", e.getMessage());
        return ExceptionsList.InvalidObject;
    }
}
