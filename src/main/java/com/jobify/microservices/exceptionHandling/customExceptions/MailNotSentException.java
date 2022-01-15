package com.jobify.microservices.exceptionHandling.customExceptions;

public class MailNotSentException extends RuntimeException{
    public MailNotSentException(String message){
        super(message);
    }
}
