package com.todo.deoji.persistence.todo.adapter

import com.todo.deoji.core.domain.todo.model.Todo
import com.todo.deoji.persistence.category.adapter.toDomain
import com.todo.deoji.persistence.category.adapter.toEntity
import com.todo.deoji.persistence.todo.entity.TodoJpaEntity

fun TodoJpaEntity.toDomain() =
    Todo(
        id = this.id,
        name = this.name,
        sort = this.sort,
        colorCode = this.colorCode,
        category = this.category.toDomain()
    )

fun Todo.toEntity() =
    TodoJpaEntity(
        id = this.id,
        name = this.name,
        sort = this.sort,
        colorCode = this.colorCode,
        category = this.category.toEntity()
    )