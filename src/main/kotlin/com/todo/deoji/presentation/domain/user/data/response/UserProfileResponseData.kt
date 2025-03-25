package com.todo.deoji.presentation.domain.user.data.response

data class UserProfileResponseData(
    val id: String,
    val name: String,
    val email: String,
    val introduce: String?,
    val profileUrl: String?,
    val profileImgUrl: String?
)
