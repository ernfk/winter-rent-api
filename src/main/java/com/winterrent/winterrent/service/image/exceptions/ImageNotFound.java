package com.winterrent.winterrent.service.image.exceptions;

public class ImageNotFound extends RuntimeException {
    public ImageNotFound() {
        super();
    }

    public ImageNotFound(String message) {
        super(message);
    }

    public ImageNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageNotFound(Throwable cause) {
        super(cause);
    }

    protected ImageNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
