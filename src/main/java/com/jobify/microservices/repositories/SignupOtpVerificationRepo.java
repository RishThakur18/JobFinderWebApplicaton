package com.jobify.microservices.repositories;

import com.jobify.microservices.entities.models.SignUpInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SignupOtpVerificationRepo extends ReactiveMongoRepository<SignUpInfo,String> {
    Mono<SignUpInfo> findSignUpInfoByEmail(String email);
}
