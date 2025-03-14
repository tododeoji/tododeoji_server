package com.todo.deoji.core.domain.auth.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.auth.dto.response.TokenResponseDto
import com.todo.deoji.core.domain.auth.spi.JwtPort

@UseCase
class LoginUseCase(
    private val jwtPort: JwtPort
) {
    fun execute(userId: String): TokenResponseDto =
        jwtPort.generateToken(userId)
}