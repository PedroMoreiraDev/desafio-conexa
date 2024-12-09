package com.conexa.saude.plataforma_medicos.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException {
    public BusinessException(String code, String message) {
        super(code, message, HttpStatus.CONFLICT);
    }
}
