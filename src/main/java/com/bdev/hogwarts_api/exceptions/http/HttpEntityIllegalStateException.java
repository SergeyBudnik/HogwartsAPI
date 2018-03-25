package com.bdev.hogwarts_api.exceptions.http;

public class HttpEntityIllegalStateException extends RuntimeException {
    public HttpEntityIllegalStateException(String formatMessage, Object... args) {
        super(String.format(formatMessage, args));
    }
}
