package com.todo.deoji.presentation.domain.auth

import com.todo.deoji.core.domain.auth.usecase.LoginUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.auth.data.extenstion.toSignInResponseData
import com.todo.deoji.presentation.domain.auth.data.response.SignInResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@WebAdapter("/auth")
class AuthWebAdapter(
    private val loginUseCase: LoginUseCase
) {
    @GetMapping("/login")
    fun googleLogin(@RequestParam(name = "username") userId: String): ResponseEntity<SignInResponseData> =
        ResponseEntity.ok(loginUseCase.execute(userId).toSignInResponseData())
}