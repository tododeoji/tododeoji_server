package com.todo.deoji.core.domain.auth.dto.response

import java.time.LocalDateTime

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: LocalDateTime,
    val refreshTokenExp: LocalDateTime
)
