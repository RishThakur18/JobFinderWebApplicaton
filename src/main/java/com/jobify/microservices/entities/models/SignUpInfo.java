package com.jobify.microservices.entities.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "signup_info")
public class SignUpInfo {
    @Id
    private String id;
    @Indexed(background = true, unique = true)
    private String email;
    private String signupOtp;
    private Integer noOfAttempts;
    private Long created;
}
