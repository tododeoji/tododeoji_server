package com.todo.deoji.presentation.domain.category.data.extension

import com.todo.deoji.core.domain.category.dto.request.AddCategoryRequestDto
import com.todo.deoji.core.domain.category.dto.request.EditCategoryDetailRequestDto
import com.todo.deoji.core.domain.category.dto.request.EditCategoryRequestDto
import com.todo.deoji.presentation.domain.category.data.request.AddCategoryRequestData
import com.todo.deoji.presentation.domain.category.data.request.EditCategoryDetailRequestData
import com.todo.deoji.presentation.domain.category.data.request.EditCategoryRequestData


fun AddCategoryRequestData.toAddCategoryRequestDto(): AddCategoryRequestDto =
    AddCategoryRequestDto(
        categoryName = this.categoryName,
        colorCode = this.colorCode
    )

fun EditCategoryDetailRequestData.toEditCategoryDetailRequestDto(): EditCategoryDetailRequestDto =
    EditCategoryDetailRequestDto(
        categoryId = this.categoryId,
        categoryName = this.categoryName,
        categoryColor = this.categoryColor
    )

fun EditCategoryRequestData.toEditCategoryRequestDtoList(): List<EditCategoryRequestDto> =
    this.categoryList
        .map {
            EditCategoryRequestDto(
                categoryId = it.categoryId,
                hideStatus = it.hideStatus,
                sort = it.sort
            )
        }