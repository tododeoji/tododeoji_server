package com.todo.deoji.infrastructure.global.jwt.adapter

import com.todo.deoji.core.domain.auth.dto.response.TokenResponseDto
import com.todo.deoji.core.domain.auth.model.Role
import com.todo.deoji.core.domain.auth.spi.JwtPort
import com.todo.deoji.infrastructure.global.jwt.properties.JwtProperty
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.security.Key
import java.time.LocalDateTime
import java.util.*

@Component
class GenerateTokenAdapter(
    private val jwtProperty: JwtProperty,
) : JwtPort {
    object JwtPrefix {
        const val ACCESS = "access"
        const val REFRESH = "refresh"
        const val ROLE = "role"
        const val PREFIX = "Bearer "
    }

    override fun generateToken(userId: String): TokenResponseDto =
        TokenResponseDto(
            accessToken = generateAccessToken(userId),
            refreshToken = generateRefreshToken(userId),
            accessTokenExp = LocalDateTime.now().withNano(0).plusSeconds(jwtProperty.accessTokenExpiredTime),
            refreshTokenExp = LocalDateTime.now().withNano(0).plusSeconds(jwtProperty.refreshTokenExpiredTime)
        )

    private fun generateAccessToken(userId: String): String =
        generateToken(jwtProperty.secretKey, JwtPrefix.ACCESS, userId, jwtProperty.accessTokenExpiredTime)

    private fun generateRefreshToken(userId: String): String =
        generateToken(jwtProperty.refreshKey, JwtPrefix.REFRESH, userId, jwtProperty.refreshTokenExpiredTime)

    private fun generateToken(secret: Key, jwtType: String, userId: String, expiredTime: Long): String {
        val now = Date()
        val expiration = Date(now.time + expiredTime * 1000)

        val authorities = arrayListOf(Role.USER)

        return Jwts.builder()
            .subject(userId)
            .claim("type", jwtType)
            .claim("roles", authorities)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(secret)
            .compact()
    }
}
