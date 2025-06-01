package com.todo.deoji.persistence.auth.repository

import com.todo.deoji.persistence.auth.entity.RefreshTokenEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshTokenEntity, String> {
    fun findByRefreshTokenAndUserId(refreshToken: String, userId: String): RefreshTokenEntity?
}