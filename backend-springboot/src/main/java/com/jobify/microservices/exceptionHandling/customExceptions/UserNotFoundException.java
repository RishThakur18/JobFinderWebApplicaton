package com.jobify.microservices.exceptionHandling.customExceptions;

import lombok.Data;


@Data
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
