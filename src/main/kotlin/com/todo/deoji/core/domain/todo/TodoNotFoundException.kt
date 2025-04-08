package com.todo.deoji.core.domain.todo

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode

class TodoNotFoundException : BasicException(ErrorCode.TODO_NOT_FOUND)