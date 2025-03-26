package com.todo.deoji.presentation.domain.user.data.extension

import com.todo.deoji.core.domain.user.dto.request.UserProfileEditRequestDto
import com.todo.deoji.presentation.domain.user.data.request.UserProfileEditRequestData

fun UserProfileEditRequestData.toDto(): UserProfileEditRequestDto =
    UserProfileEditRequestDto(
        name = this.name,
        introduce = this.introduce,
        profileImgUrl = this.profileImgUrl,
        profileUrl = this.profileUrl
    )