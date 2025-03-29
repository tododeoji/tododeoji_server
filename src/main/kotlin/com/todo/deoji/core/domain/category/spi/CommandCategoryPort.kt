package com.todo.deoji.core.domain.category.spi

import com.todo.deoji.core.domain.category.model.Category

interface CommandCategoryPort {
    fun save(category: Category): Category
    fun saveAll(categoryList: List<Category>)
}