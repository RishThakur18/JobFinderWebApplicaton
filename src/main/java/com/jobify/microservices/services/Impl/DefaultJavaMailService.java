package com.jobify.microservices.services.Impl;

import com.jobify.microservices.exceptionHandling.customExceptions.MailNotSentException;
import com.jobify.microservices.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Primary
@RequiredArgsConstructor
@Service
public class DefaultJavaMailService implements EmailService {

    @Value("${email.sender}")
    private String fromEmailId;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmailAddress, String subject, String emailContent) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(fromEmailId);
            simpleMailMessage.setTo(toEmailAddress);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(emailContent);
           try {
               javaMailSender.send(simpleMailMessage);
           }
           catch (Exception e) {
               throw new MailNotSentException(e.getMessage());
           }
    }
}
