package com.todo.deoji.presentation.domain.todo.data.request

import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import java.time.LocalDateTime

data class AddTodoRequestData(
    val todoName: String,
    val activeStatus: TodoActiveStatus,
    val categoryId: Long,
    val runDate: LocalDateTime
)
