package com.todo.deoji.core.domain.category.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.category.dto.request.EditCategoryRequestDto
import com.todo.deoji.core.domain.category.exception.CategoryNotFoundException
import com.todo.deoji.core.domain.category.spi.CategoryPort
import com.todo.deoji.core.domain.user.service.GetCurrentUserService

@UseCase
class EditCategoryUseCase(
    private val categoryPort: CategoryPort,
    private val currentUserService: GetCurrentUserService
) {
    fun execute(categorySortRequestDataList: List<EditCategoryRequestDto>) {
        val user = currentUserService.getCurrentUser()
        val categoryMap = categoryPort.findByUserId(user.id).associateBy { it.id }

        val updatedCategories = categorySortRequestDataList.map { requestDto ->
            categoryMap[requestDto.categoryId]?.copy(
                sort = requestDto.sort,
                hideStatus = requestDto.hideStatus
            )
                ?: throw CategoryNotFoundException()
        }

        categoryPort.saveAll(updatedCategories)
    }

}