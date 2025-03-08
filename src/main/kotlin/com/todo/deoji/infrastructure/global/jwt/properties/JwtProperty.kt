package com.todo.deoji.infrastructure.global.jwt.properties

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.crypto.SecretKey

@ConfigurationProperties("jwt")
class JwtProperty(
    secretKey: String,
    refreshKey: String,
    val accessTokenExpiredTime: Long,
    val refreshTokenExpiredTime: Long
) {
    val secretKey: SecretKey
    val refreshKey: SecretKey

    init {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())
        this.refreshKey = Keys.hmacShaKeyFor(refreshKey.toByteArray())
    }
}