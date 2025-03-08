package com.todo.deoji.presentation.domain.auth

import com.todo.deoji.core.domain.auth.usecase.SignInUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.auth.data.extenstion.toSignInResponseData
import com.todo.deoji.presentation.domain.auth.data.response.SignInResponseData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@WebAdapter("/auth")
class AuthWebAdapter(
    private val signInUseCase: SignInUseCase
) {
    @GetMapping("/signIn")
    fun loginSuccess(@RequestParam(name = "username") userId: String): ResponseEntity<SignInResponseData> =
        ResponseEntity.ok(signInUseCase.execute(userId).toSignInResponseData())
}