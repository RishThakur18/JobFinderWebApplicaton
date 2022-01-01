package com.jobify.microservices.repositories;

import com.jobify.microservices.entities.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveMongoRepository<User,String> {
    Mono<User> findUserByEmail(String email);
}
