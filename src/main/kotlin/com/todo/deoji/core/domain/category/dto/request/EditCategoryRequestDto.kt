package com.todo.deoji.core.domain.category.dto.request

data class EditCategoryRequestDto(
    val categoryId: Long,
    val hideStatus: Boolean,
    val sort: Int
)
