package com.todo.deoji.core.domain.category.dto.request

data class EditCategoryDetailRequestDto(
    val categoryId: Long,
    val categoryColor: String?,
    val categoryName: String?
)
