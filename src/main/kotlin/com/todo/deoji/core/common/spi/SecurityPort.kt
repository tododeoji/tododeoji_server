package com.todo.deoji.core.common.spi

interface SecurityPort {
    fun getCurrentUserId(): String
}