package com.todo.deoji.infrastructure.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.todo.deoji.core.common.exception.ErrorCode
import com.todo.deoji.infrastructure.global.exception.response.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {
    private val log = LoggerFactory.getLogger(this::class.simpleName)

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        log.error(request.method)
        log.error(request.requestURI)
        val errorCode = ErrorCode.INVALID_ROLE
        log.error(errorCode.message)
        val result = objectMapper.writeValueAsString(ErrorResponse(errorCode))

        response.characterEncoding = Charsets.UTF_8.name()
        response.status = errorCode.code
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(result)
    }
}