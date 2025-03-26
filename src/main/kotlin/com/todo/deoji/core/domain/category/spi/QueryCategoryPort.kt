package com.todo.deoji.core.domain.category.spi

import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.user.model.User

interface QueryCategoryPort {
    fun findMaxSortByUserId(userId: String): Int
    fun findById(categoryId: Long): Category
    fun findByUserId(userId: String): List<Category>
    fun findAllByUserAndIsNotHideStatus(user: User, hideStatus: Boolean): List<Category>
}