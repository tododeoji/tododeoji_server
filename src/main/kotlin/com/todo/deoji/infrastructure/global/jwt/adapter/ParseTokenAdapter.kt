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
import java.security.Key

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

        if (claims.header[Header.JWT_TYPE] != JwtPrefix.ACCESS)
            throw RuntimeException()

        val userDetails = getDetails(claims.body)

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getJwtType(token: String): String {
        val claims = getClaims(token, jwtProperty.refreshKey)

        return claims.header[Header.JWT_TYPE] as? String ?: ""
    }

    private fun getClaims(token: String, secret: Key): Jws<Claims> {

        return try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw TokenNotValidException()
                is ExpiredJwtException -> throw ExpiredTokenException()
                is JwtException -> throw TokenNotValidException()
                else -> throw RuntimeException()
            }
        }
    }

    private fun getDetails(body: Claims): UserDetails {
        val username = body.id

        return authDetailsService.loadUserByUsername(username)
    }

}