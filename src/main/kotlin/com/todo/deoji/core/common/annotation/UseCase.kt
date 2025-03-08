package com.todo.deoji.core.common.annotation

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional(rollbackFor = [Exception::class])
annotation class UseCase()
