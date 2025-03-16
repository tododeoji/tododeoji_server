package com.todo.deoji.core.domain.category.spi

import com.todo.deoji.core.domain.category.model.Category

interface QueryCategoryPort {
    fun findMaxSortByUserId(userId: String): Int
    fun findById(categoryId: Long): Category
}