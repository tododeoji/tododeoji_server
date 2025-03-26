package com.todo.deoji.presentation.domain.user

import com.todo.deoji.core.domain.user.usecase.EditMyProfileUseCase
import com.todo.deoji.core.domain.user.usecase.GetMyProfileUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.user.data.extension.toData
import com.todo.deoji.presentation.domain.user.data.extension.toDto
import com.todo.deoji.presentation.domain.user.data.request.UserProfileEditRequestData
import com.todo.deoji.presentation.domain.user.data.response.UserProfileResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter("/user")
class UserWebAdapter(
    private val getMyProfileUseCase: GetMyProfileUseCase,
    private val editMyProfileUseCase: EditMyProfileUseCase
) {
    @GetMapping("/my")
    fun getMyProfile(): ResponseEntity<UserProfileResponseData> =
        getMyProfileUseCase.execute()
            .let { ResponseEntity.ok().body(it.toData()) }

    @PatchMapping("/edit")
    fun editMyProfile(@RequestBody userProfileEditRequestData: UserProfileEditRequestData): ResponseEntity<Void> =
        editMyProfileUseCase.execute(userProfileEditRequestData.toDto())
            .let { ResponseEntity.ok().build() }
}