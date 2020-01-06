package com.bdev.hogwarts_api.rest.common

import org.springframework.http.ResponseEntity

interface CommonCRUDRest <Id, Type> {
    fun getAll(authToken: String): ResponseEntity<Any>
    fun getById(authToken: String, id: Id): ResponseEntity<Any>
    fun create(authToken: String, o: Type): ResponseEntity<Any>
    fun update(authToken: String, o: Type): ResponseEntity<Any>
    fun delete(authToken: String, id: Id): ResponseEntity<Any>
}
