package com.erickfelix.mailsender.infra;

public class NonProfitNotFoundException extends RuntimeException {
    public NonProfitNotFoundException(String message) {
        super(message);
    }

    public NonProfitNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
