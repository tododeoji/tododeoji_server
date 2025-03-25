package com.todo.deoji.presentation.domain.user

import com.todo.deoji.core.domain.user.usecase.GetMyProfileUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.user.data.extension.toData
import com.todo.deoji.presentation.domain.user.data.response.UserProfileResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@WebAdapter("/user")
class UserWebAdapter(
    private val getMyProfileUseCase: GetMyProfileUseCase
) {
    @GetMapping("/my")
    fun getMyProfile(): ResponseEntity<UserProfileResponseData> =
        getMyProfileUseCase.execute()
            .let { ResponseEntity.ok().body(it.toData()) }

}