package com.todo.deoji.core.domain.user.dto.response

data class UserProfileResponseDto(
    val id: String,
    val name: String,
    val email: String,
    val introduce: String?,
    val profileUrl: String?,
    val profileImgUrl: String?
)
