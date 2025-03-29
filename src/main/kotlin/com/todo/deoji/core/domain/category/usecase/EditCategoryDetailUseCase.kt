package com.todo.deoji.core.domain.category.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.category.dto.request.EditCategoryDetailRequestDto
import com.todo.deoji.core.domain.category.spi.CategoryPort

@UseCase
class EditCategoryDetailUseCase(
    private val categoryPort: CategoryPort
) {
    fun execute(categoryEditRequestDto: EditCategoryDetailRequestDto) {
        categoryPort.findById(categoryId = categoryEditRequestDto.categoryId)
            .let { category ->
                categoryPort.save(
                    category.copy(
                        name = categoryEditRequestDto.categoryName ?: category.name,
                        colorCode = categoryEditRequestDto.categoryColor ?: category.colorCode,
                    )
                )
            }
    }
}