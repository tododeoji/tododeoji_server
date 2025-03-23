package com.todo.deoji.presentation.domain.todo.data.response

import com.todo.deoji.core.domain.todo.dto.response.GetMainDataTodoResponseDto

data class GetMainTodoResponseData(
    val day: String,
    val todoDataList: List<GetMainDataTodoResponseDto>
)
