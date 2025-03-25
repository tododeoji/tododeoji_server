package com.todo.deoji.presentation.domain.user.data.extension

import com.todo.deoji.core.domain.user.dto.response.UserProfileResponseDto
import com.todo.deoji.presentation.domain.user.data.response.UserProfileResponseData

fun UserProfileResponseDto.toData(): UserProfileResponseData =
    UserProfileResponseData(
        id = this.id,
        name = this.name,
        introduce = this.introduce,
        email = this.email,
        profileUrl = this.profileUrl,
        profileImgUrl = this.profileImgUrl
    )