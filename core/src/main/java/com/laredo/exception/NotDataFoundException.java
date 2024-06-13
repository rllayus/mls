package com.laredo.exception;

public class NotDataFoundException extends RuntimeException {
    public NotDataFoundException(String message, Throwable cause) {
        super(message,cause);
    }
    public NotDataFoundException(String message) {
        super(message);
    }
}
