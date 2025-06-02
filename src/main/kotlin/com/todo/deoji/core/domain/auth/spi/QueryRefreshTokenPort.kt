package com.todo.deoji.core.domain.auth.spi

import com.todo.deoji.core.domain.auth.model.RefreshToken

interface QueryRefreshTokenPort {
    fun findByRefreshTokenAndUserId(refreshToken: String, userId: String): RefreshToken?
}