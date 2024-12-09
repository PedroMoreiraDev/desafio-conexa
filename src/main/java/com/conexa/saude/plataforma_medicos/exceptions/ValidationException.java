package com.conexa.saude.plataforma_medicos.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends ApiException {
    public ValidationException(String code, String message) {
        super(code, message, HttpStatus.BAD_REQUEST);
    }
}
