package com.todo.deoji.core.domain.todo.dto.response

data class GetMainListResponseDto(
    val year: Int,
    val month: Int,
    val dayDataList: List<GetDayDataResponseDto>
)
