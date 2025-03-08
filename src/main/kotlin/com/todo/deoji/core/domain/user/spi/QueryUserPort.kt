package com.todo.deoji.core.domain.user.spi

import com.todo.deoji.core.domain.user.model.User


interface QueryUserPort {
    fun findByEmail(email: String): User?

    fun findById(id: String): User?
}