package com.todo.deoji.presentation.domain.user.data.request

data class UserProfileEditRequestData(
    val name: String?,
    val profileUrl: String?,
    val profileImgUrl: String?,
    val introduce: String?
)
