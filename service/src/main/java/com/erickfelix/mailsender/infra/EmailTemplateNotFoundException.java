package com.erickfelix.mailsender.infra;

public class EmailTemplateNotFoundException extends RuntimeException {
    public EmailTemplateNotFoundException(String message) {
        super(message);
    }

    public EmailTemplateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
