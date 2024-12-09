package com.conexa.saude.plataforma_medicos.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "paciente")
@Builder
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do paciente é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF do paciente é obrigatório")
    @CPF
    private String cpf;

    private String email;

    private String senha;


}
