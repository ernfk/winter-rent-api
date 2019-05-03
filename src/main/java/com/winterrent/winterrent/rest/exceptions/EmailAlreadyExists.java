package com.winterrent.winterrent.rest.exceptions;

public class EmailAlreadyExists extends RuntimeException {

    public EmailAlreadyExists() {
        super();
    }

    public EmailAlreadyExists(String message) {
        super(message);
    }

    public EmailAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyExists(Throwable cause) {
        super(cause);
    }

    protected EmailAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
