package com.conexa.saude.plataforma_medicos;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ConexaSaudePlataformaDeMedicosApplication {

    @Value("${api.security.token.secret}")
    private String secret;

    public static void main(String[] args) {
        SpringApplication.run(ConexaSaudePlataformaDeMedicosApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTVerifier jwtVerifier() {
        return JWT.require(Algorithm.HMAC256(secret)).build();
    }

}
