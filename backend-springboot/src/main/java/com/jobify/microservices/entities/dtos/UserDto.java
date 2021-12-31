package com.jobify.microservices.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jobify.microservices.entities.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String id;

    @NotBlank(message = "firstname can not be empty")
    private String firstName;

    @NotBlank(message = "lastname can not be empty")
    private String lastName;

    @NotBlank(message = "email can not be empty")
    private String email;

    @NotBlank(message = "password can not be empty")
    private String password;

    @NotNull(message = "role can not be null")
    private List<UserRole> role;

    String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}
