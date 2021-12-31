package com.jobify.microservices.repositories;

import com.jobify.microservices.entities.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepo extends ReactiveMongoRepository<User,String> {
}
