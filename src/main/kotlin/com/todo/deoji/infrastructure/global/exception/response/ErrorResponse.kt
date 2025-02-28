package com.todo.deoji.infrastructure.global.exception.response

import com.todo.deoji.core.common.exception.ErrorCode

class ErrorResponse(
    val status: Int,
    val message: String
) {
    constructor(errorCode: ErrorCode) : this(errorCode.code, errorCode.message)
}