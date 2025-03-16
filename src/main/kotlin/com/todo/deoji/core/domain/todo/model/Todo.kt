package com.todo.deoji.core.domain.todo.model

import com.todo.deoji.core.domain.category.model.Category
import java.time.LocalDateTime

data class Todo(
    val id: Long,
    val name: String,
    val sort: Int,
    val activeStatus: TodoActiveStatus,
    val runDate: LocalDateTime,
    val category: Category
)