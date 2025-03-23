package com.todo.deoji.core.domain.todo.spi

import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.todo.dto.response.GetMainDataTodoResponseDto
import com.todo.deoji.core.domain.todo.model.Todo
import com.todo.deoji.core.domain.user.model.User
import java.time.LocalDateTime

interface QueryTodoPort {
    fun findMaxSortByCategoryAndRunDate(category: Category, runDate: LocalDateTime): Int
    fun findAllByMonthAndYearAndUser(month: Int, year: Int, user: User): List<GetMainDataTodoResponseDto>
    fun findAllByCategoryIdsAndMonthAndYear(categoryIds: List<Long>, month: Int, year: Int): List<Todo>
}