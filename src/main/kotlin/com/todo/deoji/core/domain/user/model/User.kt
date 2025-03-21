package com.todo.deoji.core.domain.user.model

import com.todo.deoji.core.domain.auth.model.Role
import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val introduce: String?,
    val profileUrl: String?,
    val profileImgUrl: String?,
    val roles: MutableList<Role>
) {
    init {
        require(email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) { "Invalid email format" }
    }
}