package com.todo.deoji.presentation.domain.category

import com.todo.deoji.core.domain.category.usecase.AddCategoryUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.category.data.extension.toAddCategoryRequestDto
import com.todo.deoji.presentation.domain.category.data.request.AddCategoryRequestData
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter("/category")
class CategoryWebAdapter(
    private val addCategoryUseCase: AddCategoryUseCase
) {
    @PostMapping
    fun addCategory(@RequestBody addCategoryRequestData: AddCategoryRequestData): ResponseEntity<Void> =
        addCategoryUseCase.execute(
            addCategoryRequestData.toAddCategoryRequestDto()
        ).run { ResponseEntity.ok().build() }
}