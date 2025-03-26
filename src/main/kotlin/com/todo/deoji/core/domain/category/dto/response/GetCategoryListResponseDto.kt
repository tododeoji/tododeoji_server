package com.todo.deoji.core.domain.category.dto.response

data class GetCategoryListResponseDto(
    val hideStatus: Boolean,
    val categoryData: List<GetCategoryResponseDto>
)
