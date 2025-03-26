package com.todo.deoji.core.domain.category.usecase

import com.todo.deoji.core.common.annotation.ReadOnlyUseCase
import com.todo.deoji.core.domain.category.dto.response.GetCategoryListResponseDto
import com.todo.deoji.core.domain.category.dto.response.GetCategoryResponseDto
import com.todo.deoji.core.domain.category.spi.CategoryPort
import com.todo.deoji.core.domain.user.service.GetCurrentUserService

@ReadOnlyUseCase
class GetCategoryListUseCase(
    private val categoryPort: CategoryPort,
    private val currentUserService: GetCurrentUserService
) {
    fun execute(): List<GetCategoryListResponseDto> =
        currentUserService.getCurrentUser()
            .let { user ->

                val categoryList = categoryPort.findByUserId(user.id)

                listOf(true, false)
                    .map { hideStatus ->
                        GetCategoryListResponseDto(
                            hideStatus = hideStatus,
                            categoryData = categoryList
                                .filter {
                                    it.hideStatus == hideStatus
                                }.map { category ->
                                    GetCategoryResponseDto(
                                        categoryId = category.id,
                                        categoryName = category.name,
                                        categoryHideStatus = category.hideStatus,
                                        categoryColor = category.colorCode,
                                        sort = category.sort
                                    )
                                }
                        )
                    }

            }

}