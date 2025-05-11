package com.todo.deoji.persistence.auth.adapter

import com.todo.deoji.core.domain.auth.model.RefreshToken
import com.todo.deoji.persistence.auth.entity.RefreshTokenEntity

fun RefreshToken.toEntity(): RefreshTokenEntity =
    RefreshTokenEntity(
        refreshToken = this.refreshToken,
        userId = this.userId
    )

fun RefreshTokenEntity.toDomain(): RefreshToken =
    RefreshToken(
        refreshToken = this.refreshToken,
        userId = userId
    )