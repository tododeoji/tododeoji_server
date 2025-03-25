package com.todo.deoji.core.domain.user.usecase

import com.todo.deoji.core.common.annotation.ReadOnlyUseCase
import com.todo.deoji.core.domain.user.dto.response.UserProfileResponseDto
import com.todo.deoji.core.domain.user.service.GetCurrentUserService

@ReadOnlyUseCase
class GetMyProfileUseCase(
    private val currentUserService: GetCurrentUserService
) {
    fun execute() =
        currentUserService.getCurrentUser()
            .let { user ->
                UserProfileResponseDto(
                    id = user.id,
                    name = user.name,
                    email = user.email,
                    introduce = user.introduce,
                    profileUrl = user.profileUrl,
                    profileImgUrl = user.profileImgUrl
                )
            }
}