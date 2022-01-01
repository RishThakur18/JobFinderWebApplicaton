package com.jobify.microservices.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Reactive API",
                version = "v1",
                description = "This documentation app provides REST APIs"),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Spring Reactive demo on localhost"
                )
        }
)

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

@Configuration
public class SwaggerOpenApiConfig {
    // Type in the url in browser
    // http://localhost:8080/webjars/swagger-ui/index.html?url=http://localhost:8080/v3/api-docs
}


