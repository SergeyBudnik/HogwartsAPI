package com.bdev.hogwarts_api.exceptions

import com.bdev.hogwarts_api.exceptions.http.*
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorController {
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @Throws(Exception::class)
    fun handleHttpMessageNotReadableException(response: HttpServletResponse, e: Exception) {
        response.status = HttpServletResponse.SC_BAD_REQUEST
        response.writer.println(e.message)
    }

    @ExceptionHandler(HttpEntityNotFoundException::class)
    @Throws(Exception::class)
    fun handleEntityNotFound(response: HttpServletResponse, e: Exception) {
        response.status = HttpServletResponse.SC_NOT_FOUND
        response.writer.println(e.message)
    }

    @ExceptionHandler(HttpEntityAlreadyExistsException::class)
    @Throws(Exception::class)
    fun handleEntityAlreadyExists(response: HttpServletResponse, e: Exception) {
        response.status = HttpServletResponse.SC_BAD_REQUEST
        response.writer.println(e.message)
    }

    @ExceptionHandler(HttpEntityIllegalStateException::class)
    @Throws(Exception::class)
    fun handleEntityIllegalState(response: HttpServletResponse, e: Exception) {
        response.status = HttpServletResponse.SC_BAD_REQUEST
        response.writer.println(e.message)
    }

    @ExceptionHandler(HttpEntityNotValidException::class)
    @Throws(Exception::class)
    fun handleEntityNotValid(response: HttpServletResponse, e: Exception) {
        response.status = HttpServletResponse.SC_BAD_REQUEST
        response.writer.println(e.message)
    }

    @ExceptionHandler(HttpCredentialsViolationException::class)
    @Throws(Exception::class)
    fun handleCredentialsViolation(response: HttpServletResponse, e: Exception) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.writer.println(e.message)
    }
}
