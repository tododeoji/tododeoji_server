package com.todo.deoji.infrastructure.global.security.auth

import com.todo.deoji.core.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User


class AuthDetails(
    private val user: User,
    private val attribute: Map<String, Any>? = null,
    private val attributeKey: String? = null
) : UserDetails, OAuth2User {
    override fun getName(): String =
        attribute!![attributeKey].toString()


    override fun getAttributes(): MutableMap<String, Any> =
        mutableMapOf("roles" to user.roles.map { SimpleGrantedAuthority(it.toString()) })


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