package com.conexa.saude.plataforma_medicos.model.entity.dto;

import com.conexa.saude.plataforma_medicos.enums.EspecialidadeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicoDTO {

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private EspecialidadeEnum especialidade;
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String telefone;


}
