package com.todo.deoji.infrastructure.global.jwt.exception

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode

class ExpiredTokenException : BasicException(ErrorCode.EXPIRED_TOKEN)