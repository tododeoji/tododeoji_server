package com.todo.deoji.core.domain.todo.dto.response

data class GetStatusTodoListResponseDto(
    val todo: List<GetMainDataTodoResponseDto>,
    val inProgress: List<GetMainDataTodoResponseDto>,
    val completed: List<GetMainDataTodoResponseDto>
)
