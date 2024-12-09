package com.conexa.saude.plataforma_medicos.repository;

import com.conexa.saude.plataforma_medicos.model.entity.Agendamento;
import com.conexa.saude.plataforma_medicos.model.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByDataHoraAndMedico(LocalDateTime dataHora, Medico medico);
}
