package com.bdev.hogwarts_api.exceptions.http

class HttpEntityIllegalStateException(formatMessage: String, vararg args: Any) :
        RuntimeException(String.format(formatMessage, *args))
