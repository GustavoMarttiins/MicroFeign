package com.br.Boleto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandle {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BoletoNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleTaskNotFoundException(BoletoNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}