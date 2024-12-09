package com.conexa.saude.plataforma_medicos.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendamento")
@Data
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

}
