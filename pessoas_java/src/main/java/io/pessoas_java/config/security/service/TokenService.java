package io.pessoas_java.config.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.pessoas_java.config.security.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final String ORIGIN = "auth-api";

    public String generateToken(UserEntity userEntity) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                .withIssuer(ORIGIN) // Quem criou/emissor
                .withSubject(userEntity.getLogin()) // Salva o login no Token para saber quem fez a requisição
                .withExpiresAt(this.generateExpirationDate())
                .sign(algorithm);

        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error while generating token.", ex);
        }
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                .withIssuer(ORIGIN)
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

