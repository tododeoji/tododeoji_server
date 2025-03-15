package com.todo.deoji.presentation.domain.category.data.extension

import com.todo.deoji.core.domain.category.dto.request.AddCategoryRequestDto
import com.todo.deoji.presentation.domain.category.data.request.AddCategoryRequestData


fun AddCategoryRequestData.toAddCategoryRequestDto(): AddCategoryRequestDto =
    AddCategoryRequestDto(
        categoryName = this.categoryName,
        colorCode = this.colorCode
    )