package com.winterrent.winterrent.service.item.exceptions;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound() {
        super();
    }

    public ItemNotFound(String message) {
        super(message);
    }

    public ItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFound(Throwable cause) {
        super(cause);
    }

    protected ItemNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
