package com.one.exception;

public class RunTimeException extends RuntimeException {

    public RunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RunTimeException(String message) {
        super(message);
    }
}
