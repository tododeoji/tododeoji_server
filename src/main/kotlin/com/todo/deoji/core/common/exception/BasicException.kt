package com.todo.deoji.core.common.exception

import java.lang.RuntimeException

open class BasicException(
    val errorCode: ErrorCode
) : RuntimeException()