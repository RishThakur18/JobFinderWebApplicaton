package com.jobify.microservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class InvalidObjectException extends RuntimeException{
    private String message;
}
