package com.jobify.microservices.exceptionHandling.customExceptions;

import lombok.Data;


@Data
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
