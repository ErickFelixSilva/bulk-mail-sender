package com.erickfelix.mailsender.config;

import com.erickfelix.mailsender.infra.EmailTemplateNotFoundException;
import com.erickfelix.mailsender.infra.NonProfitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EmailTemplateNotFoundException.class, NonProfitNotFoundException.class})
    public ResponseEntity<String> handleEmailTemplateNotFoundException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
