package com.todo.deoji.core.domain.auth.spi

import com.todo.deoji.core.domain.auth.model.RefreshToken

interface CommandRefreshTokenPort {
    fun saveRefreshToken(refreshToken: RefreshToken): RefreshToken
}