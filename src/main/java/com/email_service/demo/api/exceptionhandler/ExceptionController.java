package com.email_service.demo.api.exceptionhandler;

import com.email_service.demo.domain.exception.EmailException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;

@Log4j2
@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private String timestamp = LocalDateTime.now().toString();
    @ExceptionHandler(EmailException.class)
    ProblemDetail handlerEmailException(EmailException e){
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problem.setDetail("Error when creating the email");
        problem.setProperty("timestamp", Instant.now());
        log.error("[{}] - [ExceptionController] - EmailException: {}", timestamp, e.getMessage());
        return problem;
    }
}
