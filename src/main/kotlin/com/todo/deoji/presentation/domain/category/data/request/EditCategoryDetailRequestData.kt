package com.todo.deoji.presentation.domain.category.data.request

data class EditCategoryDetailRequestData(
    val categoryId: Long,
    val categoryColor: String?,
    val categoryName: String?
)
