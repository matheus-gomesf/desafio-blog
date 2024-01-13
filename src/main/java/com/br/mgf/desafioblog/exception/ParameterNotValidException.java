package com.br.mgf.desafioblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParameterNotValidException extends RuntimeException{

    public ParameterNotValidException(String fieldName, String fieldValue){
        super(String.format("%s can not be '%s'", fieldName, fieldValue));
    }
}