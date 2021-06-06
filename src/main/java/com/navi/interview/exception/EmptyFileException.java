package com.navi.interview.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EmptyFileException extends RuntimeException{

    private Long timestamp;
    private HttpStatus status;
    private String error;
    private String message;
    private Boolean success;

    protected EmptyFileException() {}

    public EmptyFileException(
            Long timestamp, HttpStatus status, String error, String message, Boolean success) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.success = success;
    }
}
