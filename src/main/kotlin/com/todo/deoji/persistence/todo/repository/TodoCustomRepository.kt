package com.todo.deoji.persistence.todo.repository

import com.todo.deoji.core.domain.category.model.Category
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface TodoCustomRepository {
    fun findMaxSortByCategoryAndRunDate(category: Category, runDate: LocalDateTime): Int
}