package com.todo.deoji.core.domain.auth.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.auth.dto.response.TokenResponseDto
import com.todo.deoji.core.domain.auth.model.RefreshToken
import com.todo.deoji.core.domain.auth.spi.JwtPort
import com.todo.deoji.core.domain.auth.spi.RefreshTokenPort

@UseCase
class LoginUseCase(
    private val jwtPort: JwtPort,
    private val refreshTokenPort: RefreshTokenPort
) {
    fun execute(userId: String): TokenResponseDto {
        val tokenResponseDto = jwtPort.generateToken(userId)

        refreshTokenPort.saveRefreshToken(
            RefreshToken(
                refreshToken = tokenResponseDto.refreshToken,
                userId = userId
            )
        )

        return tokenResponseDto
    }
}