package com.todo.deoji.core.domain.todo.dto.response

import com.todo.deoji.core.domain.todo.model.TodoActiveStatus

data class GetTodoDataResponseDto(
    val todoId: Long,
    val name: String,
    val todoActiveStatus: TodoActiveStatus
)