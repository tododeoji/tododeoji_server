package com.todo.deoji.infrastructure.global.exception.handler

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.infrastructure.global.exception.response.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BasicExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.simpleName)

    @ExceptionHandler(BasicException::class)
    fun handleBasicException(request: HttpServletRequest, exception: BasicException): ResponseEntity<ErrorResponse> =
        exception.errorCode
            .let {
                log.error(request.method)
                log.error(request.requestURI)
                log.error(it.message)
                ResponseEntity(
                    ErrorResponse(errorCode = it),
                    HttpStatus.valueOf(it.code)
                )
            }

}