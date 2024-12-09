package com.conexa.saude.plataforma_medicos.repository;

import com.conexa.saude.plataforma_medicos.model.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

}
