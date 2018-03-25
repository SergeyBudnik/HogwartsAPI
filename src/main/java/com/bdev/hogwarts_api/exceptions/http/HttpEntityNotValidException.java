package com.bdev.hogwarts_api.exceptions.http;

public class HttpEntityNotValidException extends RuntimeException {
    public HttpEntityNotValidException(String message, Object... args) {
        super(String.format(message, args));
    }
}
