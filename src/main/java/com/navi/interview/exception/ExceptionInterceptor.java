package com.navi.interview.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<Object> handleInputFormatExceptions(InvalidInputException ex) {
        CustomExceptionSchema exceptionResponse =
                new CustomExceptionSchema(
                        ex.getTimestamp(), ex.getStatus(), ex.getError(), ex.getMessage(),false);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderProcessingException.class)
    public final ResponseEntity<Object> handleOrderProcessingExceptions(OrderProcessingException ex) {
        CustomExceptionSchema exceptionResponse =
                new CustomExceptionSchema(
                        ex.getTimestamp(), ex.getStatus(), ex.getError(), ex.getMessage(),false);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
