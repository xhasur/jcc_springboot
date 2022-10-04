package com.crewmeister.cmcodingchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CurrencyBusinessException.class)
    public final ResponseEntity<Object> handlerExceptions(CurrencyBusinessException ex, WebRequest request){

        ExceptionResponse response =
                new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false),
                        ex.getStatus()==null? HttpStatus.BAD_REQUEST:ex.getStatus()
                );
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

}
