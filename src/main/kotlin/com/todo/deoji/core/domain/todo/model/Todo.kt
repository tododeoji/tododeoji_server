package com.todo.deoji.core.domain.todo.model

import com.todo.deoji.core.domain.category.model.Category

data class Todo(
    val id: Long,
    val name: String,
    val sort: Int,
    val colorCode: String,
    val category: Category
)