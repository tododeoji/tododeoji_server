package com.todo.deoji.infrastructure.global.security.exception

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode

class InvalidRoleException : BasicException(ErrorCode.INVALID_ROLE)