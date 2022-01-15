package com.jobify.microservices.services;

import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Validated
public interface EmailService {
    void sendEmail(@Valid String toEmailAddress, String subject, String emailContent);
}
