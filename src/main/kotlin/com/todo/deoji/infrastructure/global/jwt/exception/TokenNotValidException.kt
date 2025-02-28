package com.todo.deoji.infrastructure.global.jwt.exception

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode

class TokenNotValidException : BasicException(ErrorCode.NOT_VALID_TOKEN)