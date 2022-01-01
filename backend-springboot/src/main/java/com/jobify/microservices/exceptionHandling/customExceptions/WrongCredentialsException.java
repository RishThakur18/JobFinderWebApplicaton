package com.jobify.microservices.exceptionHandling.customExceptions;

public class WrongCredentialsException extends RuntimeException{
    public WrongCredentialsException(String message){
        super(message);
    }
}
