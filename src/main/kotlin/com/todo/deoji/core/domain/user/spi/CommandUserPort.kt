package com.todo.deoji.core.domain.user.spi

import com.todo.deoji.core.domain.user.model.User

interface CommandUserPort {
    fun save(user: User): User
}