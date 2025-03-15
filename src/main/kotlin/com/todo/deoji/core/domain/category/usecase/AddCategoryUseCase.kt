package com.todo.deoji.core.domain.category.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.category.dto.request.AddCategoryRequestDto
import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.category.spi.CategoryPort
import com.todo.deoji.core.domain.user.service.GetCurrentUserService

@UseCase
class AddCategoryUseCase(
    private val categoryPort: CategoryPort,
    private val getCurrentUserService: GetCurrentUserService
) {
    fun execute(addCategoryRequestDto: AddCategoryRequestDto) {
        getCurrentUserService.getCurrentUser()
            .let { user ->
                categoryPort.save(
                    Category(
                        id = 0,
                        name = addCategoryRequestDto.categoryName,
                        hideStatus = false,
                        sort = categoryPort.findMaxSortByUserId(user.id) + 1,
                        colorCode = addCategoryRequestDto.colorCode,
                        user = user
                    )
                )
            }
    }
}