package com.jobify.microservices.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionsList {
    ConstraintViolationException(1,"javax validation constraints failed"),
    UserNotFoundException(2,"User does not exist"),
    UserAlreadyExistsException(3,"User already exists"),
    WrongCredentialsException(4,"Invalid password");

    private final int code;
    private final String reason;

}
