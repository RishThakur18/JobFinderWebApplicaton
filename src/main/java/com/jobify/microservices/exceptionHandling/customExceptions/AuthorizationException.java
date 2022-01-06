package com.jobify.microservices.exceptionHandling.customExceptions;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message){
        super(message);
    }
}
