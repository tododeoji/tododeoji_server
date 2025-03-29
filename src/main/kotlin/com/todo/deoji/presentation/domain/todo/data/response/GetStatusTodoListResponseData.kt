package com.todo.deoji.presentation.domain.todo.data.response

import com.todo.deoji.core.domain.todo.dto.response.GetMainDataTodoResponseDto

data class GetStatusTodoListResponseData(
    val todo: List<GetMainDataTodoResponseDto>,
    val inProgress: List<GetMainDataTodoResponseDto>,
    val completed: List<GetMainDataTodoResponseDto>
)
