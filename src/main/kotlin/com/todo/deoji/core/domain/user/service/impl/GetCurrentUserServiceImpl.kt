package com.todo.deoji.core.domain.user.service.impl

import com.todo.deoji.core.common.spi.SecurityPort
import com.todo.deoji.core.domain.user.exception.UserNotFoundException
import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.core.domain.user.service.GetCurrentUserService
import com.todo.deoji.core.domain.user.spi.UserPort
import org.springframework.stereotype.Service

@Service
class GetCurrentUserServiceImpl(
    private val securityPort: SecurityPort,
    private val userPort: UserPort
) : GetCurrentUserService {
    override fun getCurrentUser(): User =
        userPort.findById(securityPort.getCurrentUserId())
            ?: throw UserNotFoundException()

}