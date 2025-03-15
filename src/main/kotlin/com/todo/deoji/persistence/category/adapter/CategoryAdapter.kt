package com.todo.deoji.persistence.category.adapter

import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.persistence.category.entity.CategoryJpaEntity
import com.todo.deoji.persistence.user.adapter.toDomain
import com.todo.deoji.persistence.user.adapter.toEntity

fun CategoryJpaEntity.toDomain() =
    Category(
        id = this.id,
        name = this.name,
        sort = this.sort,
        colorCode = this.colorCode,
        hideStatus = this.hideStatus,
        user = this.user.toDomain()
    )

fun Category.toEntity() =
    CategoryJpaEntity(
        id = this.id,
        name = this.name,
        sort = this.sort,
        colorCode = this.colorCode,
        hideStatus = this.hideStatus,
        user = this.user.toEntity()
    )