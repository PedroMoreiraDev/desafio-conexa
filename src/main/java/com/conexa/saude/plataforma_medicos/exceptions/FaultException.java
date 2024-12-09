package com.conexa.saude.plataforma_medicos.exceptions;

import java.util.List;

public class FaultException extends ApiException {
    private final List<String> details;

    public FaultException(ErrorMessage errorMessage) {
        super(errorMessage.getCode(), errorMessage.getMessage(), errorMessage.getHttpStatus());
        this.details = null;
    }

    public FaultException(ErrorMessage errorMessage, List<String> details) {
        super(errorMessage.getCode(), errorMessage.getMessage(), errorMessage.getHttpStatus());
        this.details = details;
    }

    public List<String> getDetails() {
        return details;
    }
}
