package com.br.mgf.desafioblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDeletionException extends RuntimeException{

    public InvalidDeletionException(String fieldName){
        super(String.format("This %s can only be deleted by the author", fieldName));
    }
}