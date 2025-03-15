package com.todo.deoji.persistence.category

import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.category.spi.CategoryPort
import com.todo.deoji.persistence.category.adapter.toDomain
import com.todo.deoji.persistence.category.adapter.toEntity
import com.todo.deoji.persistence.category.repository.CategoryCustomRepository
import com.todo.deoji.persistence.category.repository.CategoryJpaRepository
import org.springframework.stereotype.Component

@Component
class CategoryPersistenceAdapter(
    private val categoryJpaRepository: CategoryJpaRepository,
    private val categoryCustomRepository: CategoryCustomRepository
) : CategoryPort {
    override fun save(category: Category): Category =
        categoryJpaRepository.save(category.toEntity()).toDomain()

    override fun findMaxSortByUserId(userId: String): Int =
        categoryCustomRepository.findMaxSortByUserId(userId)

}