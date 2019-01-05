package com.bdev.hogwarts_api.exceptions.http

class HttpEntityNotValidException(message: String, vararg args: Any) :
        RuntimeException(String.format(message, *args))
