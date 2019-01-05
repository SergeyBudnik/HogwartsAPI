package com.bdev.hogwarts_api.exceptions.http

class HttpEntityNotFoundException(formatMessage: String, vararg args: Any) :
        RuntimeException(String.format(formatMessage, *args))
