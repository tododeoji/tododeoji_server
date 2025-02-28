package com.todo.deoji.infrastructure.global.jwt.adapter

import com.todo.deoji.infrastructure.global.jwt.properties.JwtProperty
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class GenerateTokenAdapter(
    private val jwtProperty: JwtProperty,
) {
    object JwtPrefix {
        const val ACCESS = "access"
        const val REFRESH = "refresh"
        const val ROLE = "role"
        const val PREFIX = "Bearer "
    }

    private fun generateAccessToken(userId: String): String =
        generateToken(jwtProperty.secretKey, JwtPrefix.ACCESS, userId, jwtProperty.accessTokenExpiredTime)

    private fun generateRefreshToken(userId: String): String =
        generateToken(jwtProperty.refreshKey, JwtPrefix.REFRESH, null, jwtProperty.refreshTokenExpiredTime)
//            .apply {
//                commandRefreshTokenPort
//                    .save(RefreshToken(userId, this, jwtProperty.refreshTokenExpiredTime))
//            }

    private fun generateToken(secret: Key, jwtType: String, userId: String? = null, expiredTime: Int): String =
        Jwts.builder()
            .signWith(secret)
            .setHeaderParam(Header.JWT_TYPE, jwtType)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiredTime * 1000))
            .apply {
                if (userId != null)
                    this.setId(userId)
            }
            .compact()
}