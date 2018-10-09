package com.company.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFlightPathException extends Exception {
    public InvalidFlightPathException(){super();}
    public InvalidFlightPathException(String message){
        super(message);
    }
}
