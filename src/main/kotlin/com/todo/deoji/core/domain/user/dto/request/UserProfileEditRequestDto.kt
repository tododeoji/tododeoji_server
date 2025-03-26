package com.todo.deoji.core.domain.user.dto.request

data class UserProfileEditRequestDto(
    val name: String?,
    val profileUrl: String?,
    val profileImgUrl: String?,
    val introduce: String?
)
