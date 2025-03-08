package com.todo.deoji.core.domain.auth.spi

import com.todo.deoji.core.domain.auth.dto.response.TokenResponseDto

interface JwtPort {
    fun generateToken(userId: String): TokenResponseDto
}