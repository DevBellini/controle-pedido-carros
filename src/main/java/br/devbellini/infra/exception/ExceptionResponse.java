package br.devbellini.infra.exception;

import br.devbellini.domain.enums.ErrorCodes;

public class ExceptionResponse extends RuntimeException {

    private final String code;
    private final String message;

    public ExceptionResponse(final ErrorCodes errorCodes, String details) {
        this.code = errorCodes.name();
        this.message = errorCodes.getMessage();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
