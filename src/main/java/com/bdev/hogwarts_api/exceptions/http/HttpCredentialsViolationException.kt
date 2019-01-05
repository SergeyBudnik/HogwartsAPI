package com.bdev.hogwarts_api.exceptions.http

class HttpCredentialsViolationException(formatMessage: String, vararg args: Any) :
        RuntimeException(String.format(formatMessage, *args))
