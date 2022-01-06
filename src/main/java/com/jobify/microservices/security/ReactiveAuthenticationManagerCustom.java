package com.jobify.microservices.security;

import com.jobify.microservices.exceptionHandling.customExceptions.AuthorizationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Log4j2
@Component
@RequiredArgsConstructor
public class ReactiveAuthenticationManagerCustom implements ReactiveAuthenticationManager {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return jwtTokenUtil.validateToken(authentication.getCredentials().toString())
                .doOnNext(isValidToken-> log.info("token valid:  {}",isValidToken))
                .filter(valid -> true)
                .switchIfEmpty(Mono.error(new AuthorizationException("Invalid token")))
                .flatMap(isValid -> jwtTokenUtil.getAllClaimsFromToken(authentication.getCredentials().toString()))
                .doOnNext(tokenData -> log.info("token data : {}",tokenData))
                .map(claims -> {
                    return new UsernamePasswordAuthenticationToken(
                            claims.getSubject(),
                            claims,
                            Collections.singletonList(new SimpleGrantedAuthority(claims.get("role").toString()))
                    );
                });
    }
}
