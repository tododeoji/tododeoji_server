package com.todo.deoji.core.domain.category.model

import com.todo.deoji.core.domain.user.model.User

data class Category(
    val id: Long,
    val name: String,
    val sort: Int,
    val colorCode: String,
    val hideStatus: Boolean,
    val user: User
)
