package com.bdev.hogwarts_api.exceptions.http;

public class HttpEntityNotFoundException extends RuntimeException {
    public HttpEntityNotFoundException(String formatMessage, Object... args) {
        super(String.format(formatMessage, args));
    }
}
