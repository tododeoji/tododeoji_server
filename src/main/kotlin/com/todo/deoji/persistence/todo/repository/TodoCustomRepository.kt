package com.todo.deoji.persistence.todo.repository

import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.todo.dto.response.GetMainDataTodoResponseDto
import com.todo.deoji.persistence.todo.entity.TodoJpaEntity
import com.todo.deoji.persistence.user.entity.UserJpaEntity
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface TodoCustomRepository {
    fun findMaxSortByCategoryAndRunDate(category: Category, runDate: LocalDateTime): Int
    fun findAllByMonthAndYearAndUser(month: Int, year: Int, user: UserJpaEntity): List<GetMainDataTodoResponseDto>
    fun findAllByCategoryAndMonthAndYear(categoryIds: List<Long>, month: Int, year: Int): List<TodoJpaEntity>
}