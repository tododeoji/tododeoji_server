package com.todo.deoji.core.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val message: String,
    val code: Int
) {
    INVALID_ROLE("유효하지않은 권한", 400),
    TOKEN_TYPE_NOT_VALID("토큰 타입이 유효하지 않음", 400),

    UNAUTHORIZED("권한이 없음", 401),
    EXPIRED_TOKEN("토큰이 만료됨", 401),
    EXPIRED_REFRESH_TOKEN("리프레시 토큰이 만료됨", 401),
    EXPIRED_AUTH_CODE("인증코드가 만료됨", 401),

    FORBIDDEN("금지된 요청", 403),
    NOT_VALID_TOKEN("토큰이 유효하지 않음", 403),
    NOT_VALID_CODE("코드가 유효하지 않음", 403),

    BAD_REQUEST("잘못된 접근입니다", 404),
    USER_NOT_FOUND("해당 유저를 찾을 수 없습니다.", 404),

    INTERNAL_ERROR("알수 없는 에러입니다", 500)
}