package com.todo.deoji.infrastructure.global.security

import com.todo.deoji.core.common.spi.SecurityPort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityAdapter : SecurityPort {
    override fun getCurrentUserId(): String =
        SecurityContextHolder.getContext().authentication.name
}