package com.todo.deoji.core.domain.auth.model

data class RefreshToken(
    val refreshToken: String,
    val userId: String
)
