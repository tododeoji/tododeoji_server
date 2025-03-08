package com.todo.deoji.infrastructure.global.security.auth

import com.todo.deoji.core.domain.user.spi.UserPort
import com.todo.deoji.infrastructure.global.jwt.exception.TokenNotValidException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService(
    private val userPort: UserPort
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        userPort.findById(username)?.let { AuthDetails(it) }
            ?: throw TokenNotValidException()
}