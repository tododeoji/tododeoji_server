package com.todo.deoji.infrastructure.global.filter

import com.todo.deoji.infrastructure.global.jwt.adapter.ParseTokenAdapter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtReqFilter(
    private val parseTokenAdapter: ParseTokenAdapter
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        resolveToken(request)
            ?.run { SecurityContextHolder.getContext().authentication = parseTokenAdapter.getAuthentication(this) }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? =
        request.getHeader("Authorization")
            ?.let { parseTokenAdapter.parseToken(it) }
}