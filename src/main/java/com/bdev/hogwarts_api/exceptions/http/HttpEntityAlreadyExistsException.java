package com.bdev.hogwarts_api.exceptions.http;

public class HttpEntityAlreadyExistsException extends RuntimeException {
    public HttpEntityAlreadyExistsException(String formatMessage, Object... args) {
        super(String.format(formatMessage, args));
    }
}
