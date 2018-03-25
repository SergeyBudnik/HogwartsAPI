package com.bdev.hogwarts_api.exceptions.http;

public class HttpCredentialsViolationException extends RuntimeException {
    public HttpCredentialsViolationException(String formatMessage, Object... args) {
        super(String.format(formatMessage, args));
    }
}
