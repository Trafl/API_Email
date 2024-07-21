package com.email_service.demo.domain.exception;

public class EmailException extends RuntimeException{
    public EmailException(String message) {
        super(message);
    }
}
