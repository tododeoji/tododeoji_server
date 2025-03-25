package com.todo.deoji.core.domain.todo.dto.request

import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import java.time.LocalDateTime

data class AddTodoRequestDto(
    val todoName: String,
    val activeStatus: TodoActiveStatus,
    val categoryId: Long,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime
)
