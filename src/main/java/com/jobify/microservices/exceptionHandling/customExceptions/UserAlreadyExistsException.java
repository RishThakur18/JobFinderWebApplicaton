package com.jobify.microservices.exceptionHandling.customExceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
