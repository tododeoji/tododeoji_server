package com.todo.deoji.core.domain.auth.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.auth.dto.response.TokenResponseDto
import com.todo.deoji.core.domain.auth.model.RefreshToken
import com.todo.deoji.core.domain.auth.spi.JwtPort
import com.todo.deoji.core.domain.auth.spi.RefreshTokenPort
import com.todo.deoji.core.domain.user.service.GetCurrentUserService
import com.todo.deoji.infrastructure.global.jwt.exception.TokenNotValidException

@UseCase
class ReIssueUseCase(
    private val refreshTokenPort: RefreshTokenPort,
    private val userService: GetCurrentUserService,
    private val jwtPort: JwtPort
) {
    fun execute(refreshToken: String): TokenResponseDto {
        userService.getCurrentUser()
            .let { user ->
                jwtPort.generateToken(
                    userId = refreshTokenPort.findByRefreshTokenAndUserId(refreshToken, user.id)
                        ?.userId ?: throw TokenNotValidException()
                ).run {
                    refreshTokenPort.saveRefreshToken(
                        RefreshToken(
                            refreshToken = this.refreshToken,
                            userId = user.id
                        )
                    )

                    return this;
                }
            }
    }
}