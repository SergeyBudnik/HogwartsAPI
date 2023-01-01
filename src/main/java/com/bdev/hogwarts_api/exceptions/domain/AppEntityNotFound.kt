package com.bdev.hogwarts_api.exceptions.domain

class AppEntityNotFound(id: String, type: String) : RuntimeException(
    "Entity with id '$id' and type '$type' does not exist"
)
