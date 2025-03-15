package com.todo.deoji.core.domain.category.spi

interface QueryCategoryPort {
    fun findMaxSortByUserId(userId: String): Int
}