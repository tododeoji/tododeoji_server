package com.todo.deoji.infrastructure.global.security.auth

import com.todo.deoji.core.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class AuthDetails(
    private val user: User
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        user.roles
            .map { SimpleGrantedAuthority(it.name) }
            .toMutableList()

    override fun getPassword(): String? = null

    override fun getUsername(): String = user.id

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}