package com.bdev.hogwarts_api.exceptions.http

class HttpEntityAlreadyExistsException(formatMessage: String, vararg args: Any) :
        RuntimeException(String.format(formatMessage, *args))
