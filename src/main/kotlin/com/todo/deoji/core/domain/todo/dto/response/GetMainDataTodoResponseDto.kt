package com.todo.deoji.core.domain.todo.dto.response

import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import java.time.LocalDateTime

data class GetMainDataTodoResponseDto(
    val id: Long,
    val activeStatus: TodoActiveStatus,
    val todoDate: LocalDateTime,
    val todoName: String,
    val categoryName: String,
    val categoryColorCode: String
)