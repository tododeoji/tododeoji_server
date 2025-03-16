package com.todo.deoji.core.domain.todo.spi

import com.todo.deoji.core.domain.category.model.Category
import java.time.LocalDateTime

interface QueryTodoPort {
    fun findMaxSortByCategoryAndRunDate(category: Category, runDate: LocalDateTime): Int
}