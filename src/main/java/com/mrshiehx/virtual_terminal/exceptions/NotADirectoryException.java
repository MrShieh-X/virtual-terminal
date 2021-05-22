package com.mrshiehx.virtual_terminal.exceptions;

public class NotADirectoryException extends RuntimeException {
    public NotADirectoryException() {
        super();
    }

    public NotADirectoryException(String message) {
        super(message);
    }

    public NotADirectoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotADirectoryException(Throwable cause) {
        super(cause);
    }

    protected NotADirectoryException(String message, Throwable cause,
                                     boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
