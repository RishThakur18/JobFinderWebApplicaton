package com.jobify.microservices.exceptionHandling;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionsList {
    ConstraintViolationException(1,"Invalid request object"),
    UserNotFoundException(2,"User does not exist! Please check the credentials ad try again. "),
    UserAlreadyExistsException(3,"User already exists with this email! please provide another user email"),
    WrongCredentialsException(4,"Access prohibited! Make sure credentials are correct"),
    AuthorizationException(5,"Not Authorized to access this content!"),
    ExpiredJwtException(6,"Not Authorized to access this content!");

    private final int code;
    private final String message;

}
