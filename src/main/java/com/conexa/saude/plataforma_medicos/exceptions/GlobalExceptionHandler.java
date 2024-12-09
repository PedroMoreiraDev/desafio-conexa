package com.conexa.saude.plataforma_medicos.exceptions;

import com.conexa.saude.plataforma_medicos.exceptions.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorMessageDTO> handleApiException(ApiException ex) {
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .details(ex instanceof FaultException faultexception ? faultexception.getDetails() : null)
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageDTO> handleGeneralException(Exception ex) {
        ErrorMessageDTO error = ErrorMessageDTO.builder()
                .code("API-PLATAFORMA-MEDICOS-UNKNOWN")
                .message("Ocorreu um erro inesperado. Entre em contato com o suporte.")
                .details(List.of(ex.getMessage()))
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

