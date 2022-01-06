package com.jobify.microservices.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Log4j2
@Component
@RequiredArgsConstructor
public class ServerSecurityContextRepositoryCustom implements ServerSecurityContextRepository {

    private final JwtTokenUtil jwtTokenUtil;
    private final ReactiveAuthenticationManagerCustom reactiveAuthenticationManagerCustom;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return null;
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest())
                .doOnNext(serverHttpRequest -> log.info("incoming request : {} ",serverHttpRequest))
                .mapNotNull(serverHttpRequest -> serverHttpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .doOnNext(headerValue ->  log.info("captured request header [AUTHORIZATION] = {} ",headerValue))
                .filter(requestHeader -> requestHeader != null && requestHeader.startsWith("Bearer "))
                .map(requestHeaderValue -> requestHeaderValue.substring(7))
                .doOnNext(token ->  log.info("token value : {} ",token))
                .map(token -> new UsernamePasswordAuthenticationToken(null,token))
                .flatMap(reactiveAuthenticationManagerCustom::authenticate)
                .doOnNext(authentication -> log.info("Authentication : {}",authentication))
                .map(SecurityContextImpl::new);
    }
}
