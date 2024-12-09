package com.conexa.saude.plataforma_medicos.model.entity;

import com.conexa.saude.plataforma_medicos.enums.EspecialidadeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Builder
@Table(name = "medico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Email do médico é obrigatório")
    @Email(message = "Informe um e-mail válido.")
    private String email;

    @NotBlank(message = "A senha do médico é obrigatório")
    private String senha;

    @NotNull(message = "A especialidade do médico é obrigatório")
    @Enumerated(EnumType.STRING)
    private EspecialidadeEnum especialidade;

    @NotBlank(message = "O CPF do médico é obrigatório")
    @CPF
    private String cpf;

    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}")
    private String telefone;
}
