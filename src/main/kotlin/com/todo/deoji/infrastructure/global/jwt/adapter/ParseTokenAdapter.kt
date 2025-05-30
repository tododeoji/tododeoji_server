package com.todo.deoji.infrastructure.global.jwt.adapter

import com.todo.deoji.infrastructure.global.jwt.exception.ExpiredTokenException
import com.todo.deoji.infrastructure.global.jwt.exception.TokenNotValidException
import com.todo.deoji.infrastructure.global.jwt.properties.JwtProperty
import com.todo.deoji.infrastructure.global.security.auth.AuthDetailsService
import io.jsonwebtoken.*
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class ParseTokenAdapter(
    private val authDetailsService: AuthDetailsService,
    private val jwtProperty: JwtProperty
) {
    object JwtPrefix {
        const val ACCESS = "access"
        const val REFRESH = "refresh"
        const val ROLE = "role"
        const val PREFIX = "Bearer "
    }

    fun parseToken(token: String): String? =
        if (token.startsWith(JwtPrefix.PREFIX)) token.substring(JwtPrefix.PREFIX.length) else null

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token, jwtProperty.secretKey)

        if (claims.payload["type"] != JwtPrefix.ACCESS)
            throw TokenNotValidException()

        val userDetails = getDetails(claims.payload)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getClaims(token: String, secret: SecretKey): Jws<Claims> =
        runCatching {
            Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
        }.getOrElse { exception ->
            throw when (exception) {
                is InvalidClaimException -> TokenNotValidException()
                is ExpiredJwtException -> ExpiredTokenException()
                is JwtException -> TokenNotValidException()
                else -> RuntimeException()
            }
        }

    private fun getDetails(body: Claims): UserDetails =
        body.subject
            .let { authDetailsService.loadUserByUsername(it) }

}
