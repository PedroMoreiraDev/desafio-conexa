package com.conexa.saude.plataforma_medicos.repository;

import com.conexa.saude.plataforma_medicos.model.entity.JWTBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JWTBlacklistRepository extends JpaRepository<JWTBlacklist, Long> {

    Optional<JWTBlacklist> findByToken(String token);
}
