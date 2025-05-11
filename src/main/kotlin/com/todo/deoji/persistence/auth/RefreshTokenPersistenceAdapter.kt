package com.todo.deoji.persistence.auth

import com.todo.deoji.core.domain.auth.model.RefreshToken
import com.todo.deoji.core.domain.auth.spi.RefreshTokenPort
import com.todo.deoji.persistence.auth.adapter.toDomain
import com.todo.deoji.persistence.auth.adapter.toEntity
import com.todo.deoji.persistence.auth.repository.RefreshTokenRepository
import org.springframework.stereotype.Component

@Component
class RefreshTokenPersistenceAdapter(
    private val refreshTokenRepository: RefreshTokenRepository
) : RefreshTokenPort {
    override fun saveRefreshToken(refreshToken: RefreshToken): RefreshToken =
        refreshTokenRepository.save(refreshToken.toEntity()).toDomain()
}