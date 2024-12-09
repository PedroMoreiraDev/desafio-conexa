package com.conexa.saude.plataforma_medicos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum EspecialidadeEnum {
    ACUPUNTURA("Acupuntura"),
    ALERGOLOGIA("Alergologia"),
    ANESTESIOLOGIA("Anestesiologia"),
    ANGIOLOGIA("Angiologia"),
    CARDIOLOGISTA("Cardiologista"),
    CIRURGIA_GERAL("Cirurgião Geral"),
    CIRURGIA_PLASTICA("Cirurgião Plástico"),
    CIRURGIA_CARDIOVASCULAR("Cirurgião Cardiovascular"),
    CIRURGIA_PEDIATRICA("Cirurgião Pediátrico"),
    CIRURGIA_TORACICA("Cirurgião Torácico"),
    CLINICA_MEDICA("Clínico Geral"),
    DERMATOLOGIA("Dermatologista"),
    ENDOCRINOLOGIA("Endocrinologista"),
    ENFERMAGEM("Enfermeiro(a)"),
    FISIOTERAPIA("Fisioterapeuta"),
    GASTROENTEROLOGIA("Gastroenterologista"),
    GERIATRIA("Geriatra"),
    GINECOLOGIA("Ginecologista"),
    HEMATOLOGIA("Hematologista"),
    HEPATOLOGIA("Hepatologista"),
    HOMEOPATIA("Homeopata"),
    INFECTOLOGIA("Infectologista"),
    MEDICINA_ESPORTIVA("Médico(a) Esportivo(a)"),
    MEDICINA_DO_TRABALHO("Médico(a) do Trabalho"),
    MEDICINA_FAMILIAR("Médico(a) de Família"),
    NEFROLOGIA("Nefrologista"),
    NEUROLOGIA("Neurologista"),
    NUTRICAO("Nutricionista"),
    OFTALMOLOGIA("Oftalmologista"),
    ONCOLOGIA("Oncologista"),
    ORTOPEDIA("Ortopedista"),
    OTORRINOLARINGOLOGIA("Otorrinolaringologista"),
    PEDIATRIA("Pediatra"),
    PSIQUIATRIA("Psiquiatra"),
    PSICOLOGIA("Psicólogo(a)"),
    RADIOLOGIA("Radiologista"),
    REUMATOLOGIA("Reumatologista"),
    UROLOGIA("Urologista"),
    OUTRA("Outra");

    private final String nomeFormatado;

    EspecialidadeEnum(String nomeFormatado) {
        this.nomeFormatado = nomeFormatado;
    }

    @JsonCreator
    public static EspecialidadeEnum fromString(String nomeFormatado) {
        for (EspecialidadeEnum especialidade : EspecialidadeEnum.values()) {
            if (especialidade.nomeFormatado.equalsIgnoreCase(nomeFormatado)) {
                return especialidade;
            }
        }
        throw new IllegalArgumentException("Especialidade inválida. Envie uma das especialidades da lista: " +
                Arrays.toString(EspecialidadeEnum.values())
        );
    }

    public String getNomeFormatado() {
        return nomeFormatado;
    }

    @Override
    public String toString() {
        return nomeFormatado;
    }
}
