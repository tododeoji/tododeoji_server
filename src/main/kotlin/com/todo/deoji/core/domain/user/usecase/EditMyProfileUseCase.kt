package com.todo.deoji.core.domain.user.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.user.dto.request.UserProfileEditRequestDto
import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.core.domain.user.service.GetCurrentUserService
import com.todo.deoji.core.domain.user.spi.UserPort

@UseCase
class EditMyProfileUseCase(
    private val userPort: UserPort,
    private val currentUserService: GetCurrentUserService
) {
    fun execute(userProfileEditRequestDto: UserProfileEditRequestDto) {
        currentUserService.getCurrentUser()
            .let {
                userPort.save(
                    User(
                        id = it.id,
                        email = it.email,
                        name = userProfileEditRequestDto.name ?: it.name,
                        introduce = userProfileEditRequestDto.introduce ?: it.introduce,
                        profileUrl = userProfileEditRequestDto.profileUrl ?: it.profileUrl,
                        profileImgUrl = userProfileEditRequestDto.profileImgUrl ?: it.profileImgUrl,
                        roles = it.roles
                    )
                )
            }
    }
}