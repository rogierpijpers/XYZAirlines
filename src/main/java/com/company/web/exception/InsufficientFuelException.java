package com.company.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientFuelException extends Exception {
    public InsufficientFuelException(){
        super();
    }

    public InsufficientFuelException(String message){
        super(message);
    }
}
