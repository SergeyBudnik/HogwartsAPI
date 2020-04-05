package com.bdev.hogwarts_api.rest.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object CommonResponse {
    object Success {
        fun withBody(body: Any): ResponseEntity<Any> {
            return ResponseEntity.status(HttpStatus.OK).body(body)
        }

        fun empty(): ResponseEntity<Any> {
            return ResponseEntity.status(HttpStatus.OK).build()
        }
    }

    object NotFound {
        fun withMessage(message: String): ResponseEntity<Any> {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message)
        }
    }
}