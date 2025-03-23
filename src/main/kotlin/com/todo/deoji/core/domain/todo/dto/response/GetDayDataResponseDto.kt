package com.todo.deoji.core.domain.todo.dto.response

data class GetDayDataResponseDto(
    val day: Int,
    val categoryDataList: List<GetCategoryMainDataResponseDto>
)
