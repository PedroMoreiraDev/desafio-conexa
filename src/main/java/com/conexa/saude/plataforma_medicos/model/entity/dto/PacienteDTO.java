package com.conexa.saude.plataforma_medicos.model.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

}
