package com.jobify.microservices.utilities;

import com.jobify.microservices.entities.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
    public class JwtTokenUtil implements Serializable {


    private final byte[] SIGNING_KEY ="sfasf".getBytes(StandardCharsets.UTF_8);

    private final int ACCESS_TOKEN_VALIDITY_SECONDS=3;

    public String getUsernameFromToken(String token) {
            return getClaimFromToken(token, Claims::getSubject);
        }

        public Date getExpirationDateFromToken(String token) {
            return getClaimFromToken(token, Claims::getExpiration);
        }

        public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }

        public Claims getAllClaimsFromToken(String token) {
            return Jwts.parser()
                    .setSigningKey(SIGNING_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        }

        private Boolean isTokenExpired(String token) {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

        public Mono<String> generateToken(User user) {
            Claims claims = Jwts.claims().setSubject(user.getEmail());
            claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

            return Mono.just(Jwts.builder()
                    .setClaims(claims)
                    .setIssuer("rishabhSingh18")
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                    .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                    .compact());
        }

        public Boolean validateToken(String token, UserDetails userDetails) {
            final String username = getUsernameFromToken(token);
            return (
                    username.equals(userDetails.getUsername())
                            && !isTokenExpired(token));
        }

    }

