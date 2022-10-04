package com.crewmeister.cmcodingchallenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CurrencyBusinessException extends RuntimeException {

    private HttpStatus status;

    public CurrencyBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyBusinessException(String message) {
        super(message);
    }

    public CurrencyBusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }
}
