package com.jobify.microservices.exceptionHandling.customExceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
