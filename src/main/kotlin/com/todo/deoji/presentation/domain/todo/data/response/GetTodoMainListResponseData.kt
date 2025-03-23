package com.todo.deoji.presentation.domain.todo.data.response

import com.todo.deoji.core.domain.todo.dto.response.GetDayDataResponseDto

data class GetTodoMainListResponseData(
    val year: Int,
    val month: Int,
    val dayDataList: List<GetDayDataResponseDto>
)
