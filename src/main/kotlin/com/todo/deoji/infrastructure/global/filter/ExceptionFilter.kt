package com.todo.deoji.infrastructure.global.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode
import com.todo.deoji.infrastructure.global.exception.response.ErrorResponse
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter

class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    private val log = LoggerFactory.getLogger(this::class.simpleName)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        runCatching { filterChain.doFilter(request, response) }
            .onFailure { exception ->
                val (errorCode, ex) = when (exception) {
                    is BasicException -> exception.errorCode to exception
                    is ServletException -> ErrorCode.BAD_REQUEST to BasicException(ErrorCode.BAD_REQUEST)
                    else -> ErrorCode.INTERNAL_ERROR to BasicException(ErrorCode.INTERNAL_ERROR)
                }

                logErrorResponse(errorCode, exception)
                writeErrorResponse(response, ex)
            }
    }

    private fun logErrorResponse(errorCode: ErrorCode, exception: Throwable) {
        log.error(errorCode.message)
        log.error(exception.message)
    }

    private fun writeErrorResponse(response: HttpServletResponse, exception: BasicException) {
        val errorCode = exception.errorCode
        val responseBody = objectMapper.writeValueAsString(ErrorResponse(errorCode))
        response.status = errorCode.code
        response.characterEncoding = Charsets.UTF_8.name()
        response.contentType = "application/json"
        response.writer.write(responseBody)
    }
}