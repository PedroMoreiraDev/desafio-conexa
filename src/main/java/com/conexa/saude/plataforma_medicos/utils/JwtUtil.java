package com.conexa.saude.plataforma_medicos.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.conexa.saude.plataforma_medicos.exceptions.ErrorMessage;
import com.conexa.saude.plataforma_medicos.exceptions.InternalServerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.conexa.saude.plataforma_medicos.utils.Constants.AUTH_API;
import static com.conexa.saude.plataforma_medicos.utils.Constants.TEMPO_EXPIRACAO_TOKEN_15_MINUTOS;


@Component
public class JwtUtil {

    @Value("${api.security.token.secret}")
    private String secret;

    private JWTVerifier jwtVerifier;

    public JwtUtil() {
    }

    public JwtUtil(JWTVerifier jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
    }

    public String generateToken(String email) {
        try {
            return JWT.create()
                    .withIssuer(AUTH_API)
                    .withSubject(email)
                    .withExpiresAt(new Date(System.currentTimeMillis() + TEMPO_EXPIRACAO_TOKEN_15_MINUTOS))
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            throw new InternalServerException(ErrorMessage.ERRO_AO_GERAR_JWT_TOKEN.getCode(),
                    ErrorMessage.ERRO_AO_GERAR_JWT_TOKEN.getMessage());
        }
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = getJwtVerifier();
            DecodedJWT decodedJWT = verifier.verify(token);
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    public String getSubject(String token) {
        try {
            JWTVerifier verifier = getJwtVerifier();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            throw new InternalServerException(ErrorMessage.ERRO_AO_RECUPERAR_SUBJECT_JWT_TOKEN.getCode(),
                    ErrorMessage.ERRO_AO_RECUPERAR_SUBJECT_JWT_TOKEN.getMessage());
        }
    }

    private JWTVerifier getJwtVerifier() {
        if (jwtVerifier == null) {
            jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(AUTH_API).build();
        }
        return jwtVerifier;
    }

    public void setSecret(String secret) {
        this.secret = secret;
        this.jwtVerifier = null;
    }
}