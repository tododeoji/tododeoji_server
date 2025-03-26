package com.todo.deoji.presentation.domain.category.data.extension

import com.todo.deoji.core.domain.category.dto.response.GetCategoryListResponseDto
import com.todo.deoji.core.domain.category.dto.response.GetCategoryResponseDto
import com.todo.deoji.presentation.domain.category.data.response.GetCategoryListResponseData

fun GetCategoryListResponseDto.toData(): GetCategoryListResponseData =
    GetCategoryListResponseData(
        hideStatus = this.hideStatus,
        categoryData = this.categoryData
    )