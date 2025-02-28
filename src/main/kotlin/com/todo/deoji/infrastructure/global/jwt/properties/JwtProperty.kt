package com.todo.deoji.infrastructure.global.jwt.properties

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import java.security.Key

@ConfigurationProperties("jwt")
class JwtProperty(
    secretKey: String,
    refreshKey: String,
    val accessTokenExpiredTime: Int,
    val refreshTokenExpiredTime: Int
) {
    val secretKey: Key
    val refreshKey: Key

    init {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
        this.refreshKey = Keys.hmacShaKeyFor(refreshKey.toByteArray())
    }
}