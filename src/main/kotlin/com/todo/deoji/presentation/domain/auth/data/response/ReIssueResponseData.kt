package com.todo.deoji.presentation.domain.auth.data.response

import java.time.LocalDateTime

data class ReIssueResponseData(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: LocalDateTime,
    val refreshTokenExp: LocalDateTime
)
