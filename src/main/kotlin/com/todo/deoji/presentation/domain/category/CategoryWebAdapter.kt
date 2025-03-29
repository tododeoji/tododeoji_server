package com.todo.deoji.presentation.domain.category

import com.todo.deoji.core.domain.category.usecase.AddCategoryUseCase
import com.todo.deoji.core.domain.category.usecase.EditCategoryUseCase
import com.todo.deoji.core.domain.category.usecase.EditCategoryDetailUseCase
import com.todo.deoji.core.domain.category.usecase.GetCategoryListUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.category.data.extension.*
import com.todo.deoji.presentation.domain.category.data.request.AddCategoryRequestData
import com.todo.deoji.presentation.domain.category.data.request.EditCategoryDetailRequestData
import com.todo.deoji.presentation.domain.category.data.request.EditCategoryRequestData
import com.todo.deoji.presentation.domain.category.data.response.GetCategoryListResponseData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter("/category")
class CategoryWebAdapter(
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val editCategoryDetailUseCase: EditCategoryDetailUseCase,
    private val editCategoryUseCase: EditCategoryUseCase
) {
    @PostMapping
    fun addCategory(@RequestBody addCategoryRequestData: AddCategoryRequestData): ResponseEntity<Void> =
        addCategoryUseCase.execute(
            addCategoryRequestData.toAddCategoryRequestDto()
        ).run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping("/list")
    fun getUserCategory(): ResponseEntity<List<GetCategoryListResponseData>> =
        ResponseEntity.ok().body(getCategoryListUseCase.execute().map { it.toData() })

    @PatchMapping("/edit-detail")
    fun editCategory(@RequestBody editCategoryRequestData: EditCategoryDetailRequestData): ResponseEntity<Void> =
        editCategoryDetailUseCase.execute(editCategoryRequestData.toEditCategoryDetailRequestDto())
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PatchMapping("/edit")
    fun editCategorySort(@RequestBody editCategorySortRequestData: EditCategoryRequestData): ResponseEntity<Void> =
        editCategoryUseCase.execute(editCategorySortRequestData.toEditCategoryRequestDtoList())
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
}