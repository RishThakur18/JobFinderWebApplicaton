package com.jobify.microservices.configurations;

import com.jobify.microservices.security.ServerSecurityContextRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final ServerSecurityContextRepositoryCustom serverSecurityContextRepositoryCustom;

    private static final String[] WHITELISTED_URLS = {
            "/health",
            "/auth/**",
            "/webjars/**",
            "/v3/api-docs/**",
            "/test",
            "/test/**"
    };

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .formLogin().disable()
                .securityContextRepository(serverSecurityContextRepositoryCustom)
                .authorizeExchange()
                .pathMatchers(WHITELISTED_URLS).permitAll()
                .anyExchange().authenticated()
                .and().build();
    }

    @Bean
    public PasswordEncoder setPasswordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

}
