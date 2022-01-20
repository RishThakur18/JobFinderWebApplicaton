package com.jobify.microservices.entities.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import static com.jobify.microservices.utilities.StaticConstantsUtil.NOT_BLANK;


@Data
@AllArgsConstructor
public class LoginRequestDto {

//    @NotBlank(message = "email" + NOT_BLANK)
    @Email
    private String email;

    @NotBlank(message = "password" + NOT_BLANK)
    private String password;
}
