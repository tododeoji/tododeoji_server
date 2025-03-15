package com.todo.deoji.core.domain.user.exception

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode

class UserNotFoundException : BasicException(ErrorCode.USER_NOT_FOUND)