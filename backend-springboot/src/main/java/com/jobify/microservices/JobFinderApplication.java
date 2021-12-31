package com.jobify.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class JobFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobFinderApplication.class, args);
	}

	@Bean
	public WebProperties.Resources getResources(){
		return new WebProperties.Resources();
	}

}
