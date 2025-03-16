package com.todo.deoji.core.domain.category.exception

import com.todo.deoji.core.common.exception.BasicException
import com.todo.deoji.core.common.exception.ErrorCode

class CategoryNotFoundException : BasicException(ErrorCode.CATEGORY_NOT_FOUND) {
}