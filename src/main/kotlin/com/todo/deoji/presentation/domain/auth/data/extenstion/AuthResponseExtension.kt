package com.todo.deoji.presentation.domain.auth.data.extenstion

import com.todo.deoji.core.domain.auth.dto.response.TokenResponseDto
import com.todo.deoji.presentation.domain.auth.data.response.ReIssueResponseData
import com.todo.deoji.presentation.domain.auth.data.response.SignInResponseData

fun TokenResponseDto.toSignInResponseData(): SignInResponseData =
    SignInResponseData(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        accessTokenExp = this.accessTokenExp,
        refreshTokenExp = this.refreshTokenExp
    )

fun TokenResponseDto.toReIssueResponseData(): ReIssueResponseData =
    ReIssueResponseData(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        accessTokenExp = this.accessTokenExp,
        refreshTokenExp = this.refreshTokenExp
    )