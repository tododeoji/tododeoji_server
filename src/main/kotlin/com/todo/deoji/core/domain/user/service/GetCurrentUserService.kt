package com.todo.deoji.core.domain.user.service

import com.todo.deoji.core.domain.user.model.User

interface GetCurrentUserService {
    fun getCurrentUser(): User
}