package com.todo.deoji.core.domain.todo.dto.response

data class GetMainDataResponseDto(
    val day: String,
    val todoList: List<GetMainDataTodoResponseDto>
)
