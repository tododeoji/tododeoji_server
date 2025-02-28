package com.todo.deoji.infrastructure.global.exception.handler

import com.todo.deoji.core.common.exception.ErrorCode
import com.todo.deoji.infrastructure.global.exception.response.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class SpringExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.simpleName)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(
        request: HttpServletRequest,
        exception: MethodArgumentNotValidException
    ): ResponseEntity<ErrorResponse> {
        log.error(request.method)
        log.error(request.requestURI)
        val errorCode = ErrorCode.BAD_REQUEST
        log.error(errorCode.message)
        return ResponseEntity(
            ErrorResponse(errorCode),
            HttpStatusCode.valueOf(errorCode.code)
        )
    }
}