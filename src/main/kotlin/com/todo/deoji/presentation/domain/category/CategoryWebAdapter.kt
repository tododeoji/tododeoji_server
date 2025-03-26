package com.todo.deoji.presentation.domain.category

import com.todo.deoji.core.domain.category.dto.response.GetCategoryListResponseDto
import com.todo.deoji.core.domain.category.usecase.AddCategoryUseCase
import com.todo.deoji.core.domain.category.usecase.GetCategoryListUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.category.data.extension.toAddCategoryRequestDto
import com.todo.deoji.presentation.domain.category.data.extension.toData
import com.todo.deoji.presentation.domain.category.data.request.AddCategoryRequestData
import com.todo.deoji.presentation.domain.category.data.response.GetCategoryListResponseData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter("/category")
class CategoryWebAdapter(
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase
) {
    @PostMapping
    fun addCategory(@RequestBody addCategoryRequestData: AddCategoryRequestData): ResponseEntity<Void> =
        addCategoryUseCase.execute(
            addCategoryRequestData.toAddCategoryRequestDto()
        ).run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("/list")
    fun getUserCategory(): ResponseEntity<List<GetCategoryListResponseData>> =
        ResponseEntity.ok().body(getCategoryListUseCase.execute().map { it.toData() })
}