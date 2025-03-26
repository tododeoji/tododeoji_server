package com.todo.deoji.core.domain.category.dto.response

data class GetCategoryResponseDto(
    val categoryId: Long,
    val categoryName: String,
    val categoryHideStatus: Boolean,
    val categoryColor: String,
    val sort: Int
)
