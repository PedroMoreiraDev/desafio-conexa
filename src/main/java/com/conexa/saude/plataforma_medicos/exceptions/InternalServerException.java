package com.conexa.saude.plataforma_medicos.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerException extends ApiException {
    public InternalServerException(String code, String message) {
        super(code, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
