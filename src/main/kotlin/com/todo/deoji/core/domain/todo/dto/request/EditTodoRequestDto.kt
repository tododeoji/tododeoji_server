package com.todo.deoji.core.domain.todo.dto.request

import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import java.time.LocalDateTime

data class EditTodoRequestDto(
    val id: Long,
    val todoName: String,
    val activeStatus: TodoActiveStatus,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val sort: Int
)
