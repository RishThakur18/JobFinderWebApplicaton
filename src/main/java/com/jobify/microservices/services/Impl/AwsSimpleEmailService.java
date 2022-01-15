package com.jobify.microservices.services.Impl;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.jobify.microservices.services.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Order(1)
@Log4j2
@RequiredArgsConstructor
@Service
public class AwsSimpleEmailService implements EmailService {

    @Value("${email.sender}")
    private String fromEmailId;

    private final AmazonSimpleEmailService sesClient;

    public void sendEmail(String toEmailAddress, String subject, String emailContent) {
        log.info("sending email via AWS SES : [toEmailAddress] {} [subject) {} (emailContent) {}", toEmailAddress, subject, emailContent);
        Mono.just(new SendEmailRequest()
                        .withSource(fromEmailId)
                        .withDestination(new Destination(List.of(fromEmailId)))
                        .withMessage(new Message()
                                        .withSubject(new Content(subject))
                                        .withBody(new Body(new Content(emailContent)))))
                .map(sesClient::sendEmail)
                .doOnNext(result -> log.info("message id:{}", result.getMessageId()))
                .map(result -> true)
                .switchIfEmpty(Mono.just(false))
                .subscribe();
    }
}


