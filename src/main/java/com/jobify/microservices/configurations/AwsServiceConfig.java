package com.jobify.microservices.configurations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class AwsServiceConfig  {

    @Value("AKIA23MKOMZOSA7QJYAY")
    private String accessKey;

    @Value("tjBUiA64PTAjlE8BunsjqZh5meEHzS8R93T6gz/K")
    private String secretKey;

//    @Bean
//    public AmazonS3 createAmazonS3() {
//
//        log.info("createAmazonS3 -> (accessKey) {} (secretKey) {}", accessKey, secretKey);
//        return AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
//                .withRegion(Regions.EU_WEST_1)
//                .build();
//    }

    @Bean
    public AmazonSimpleEmailService createAmazonSes() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }

}
