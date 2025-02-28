package com.todo.deoji.infrastructure.global.jwt.exception

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode

class TokenTypeNotValidException : BasicException(ErrorCode.TOKEN_TYPE_NOT_VALID)