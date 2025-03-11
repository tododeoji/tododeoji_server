package com.todo.deoji.infrastructure.global.oauth.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component

@Component
class CustomOAuth2FailureHandler : AuthenticationFailureHandler{
    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        println("OAuth 로그인 실패 에러 메시지: ${exception!!.message}")
    }
}