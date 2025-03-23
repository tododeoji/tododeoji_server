package com.todo.deoji.core.domain.todo.dto.response

data class GetCategoryMainDataResponseDto(
    val categoryId: Long,
    val categoryName: String,
    val colorCode: String,
    val todoDataList: List<GetTodoDataResponseDto>
)
