package com.mrshiehx.virtual_terminal.exceptions;

public class LoadUserException extends RuntimeException {
    public LoadUserException() {
        super();
    }

    public LoadUserException(String message) {
        super(message);
    }

    public LoadUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadUserException(Throwable cause) {
        super(cause);
    }

    protected LoadUserException(String message, Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}