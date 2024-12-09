package com.conexa.saude.plataforma_medicos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    CPF_MEDICO_JA_CADASTRADO("API-PLATAFORMA-MEDICOS-0001", "CPF do médico já cadastrado no banco de dados", 400),
    EMAIL_MEDICO_JA_CADASTRADO("API-PLATAFORMA-MEDICOS-0002", "EMAIL do médico já cadastrado no banco de dados", 400),
    USUARIO_NAO_LOCALIZADO("API-PLATAFORMA-MEDICOS-0003", "Usuario não localizado no banco de dados", 400),
    SENHA_INCORRETA("API-PLATAFORMA-MEDICOS-0004", "Senha Incorreta", 400),
    ERRO_AO_GERAR_JWT_TOKEN("API-PLATAFORMA-MEDICOS-0005", "Erro ao gerar JWT Token", 500),
    ERRO_AO_RECUPERAR_SUBJECT_JWT_TOKEN("API-PLATAFORMA-MEDICOS-0005", "Erro ao recuperar subject JWT Token", 500),
    DATA_INVALIDA("API-PLATAFORMA-MEDICOS-0006", "Só é permitido agendar para datas no futuro", 500),
    HORARIO_OCUPADO("API-PLATAFORMA-MEDICOS-0007", "O horário informado já está ocupado", 500),


    ERRO_AO_VERIFICAR_JWT("API-PLATAFORMA-MEDICOS-0006", "Erro ao verificar JWT Token", 500),


    SAVE_TABLE_TBOD_CONTROLE_VISUALIZACAO_ERROR("API-PLATAFORMA-MEDICOS-0002", "Erro ao inserir na TBOD_CONTROLE_VISUALIZACAO id video: %s", 400);

    private String code;
    private String message;
    private Integer httpCode;

    public HttpStatus getHttpStatus() {
        HttpStatus httpStatus = HttpStatus.resolve(httpCode);
        return httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getFormattedMessage(Object... args) {
        return String.format(this.message, args);
    }
}
