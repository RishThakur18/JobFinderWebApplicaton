package com.jobify.microservices.configurations;

import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class OtpGeneratorConfig {
    public String generateOtp() {
        return String.valueOf(new Random().nextInt(100000, 1000000));
    }
}