package com.jobify.microservices.security;

import com.jobify.microservices.entities.models.User;
import com.jobify.microservices.repositories.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static com.jobify.microservices.utilities.StaticConstantsUtil.*;

@Log4j2
@RequiredArgsConstructor
@Component
    public class JwtTokenUtil implements Serializable {

    private final UserRepo userRepo;

    public Mono<String> generateToken(User user) {
        return Mono.just(Jwts.claims().setSubject(user.getEmail()))
                .doOnNext(claims ->  claims.put("role", user.getRole()))
                        .map(claims -> Jwts.builder()
                                .setClaims(claims)
                                .setIssuer(ISSUER)
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
                                .compact());
    }

    public Mono<Boolean> validateToken(String token) {
        final boolean validUser = true;
        return getUsernameFromToken(token)
                .doOnNext(email -> log.info("extracted from token [email] : {}",email))
                .flatMap(userRepo::findUserByEmail)
                .map(user -> validUser)
                .switchIfEmpty(Mono.just(!validUser))
                .doOnNext(isUserValid -> log.info("user valid : {}",isUserValid))
                .zipWith(isTokenExpired(token))
                .doOnNext(data -> log.info("is token expired : {}",data.getT2()))
                .map(data-> data.getT1() && !data.getT2());
    }

    public Mono<Claims> getAllClaimsFromToken(String token) {
        return Mono.just(Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody());
    }

    private Mono<String> getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token)
                .map(Claims::getSubject);
    }

    private Mono<Boolean> isTokenExpired(String token) {
        return getAllClaimsFromToken(token)
                .map(Claims::getExpiration)
                .map(date -> date.before(new Date()));
    }

}

