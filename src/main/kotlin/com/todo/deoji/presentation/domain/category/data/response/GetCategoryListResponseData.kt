package com.todo.deoji.presentation.domain.category.data.response

import com.todo.deoji.core.domain.category.dto.response.GetCategoryResponseDto

data class GetCategoryListResponseData(
    val hideStatus: Boolean,
    val categoryData: List<GetCategoryResponseDto>
)
