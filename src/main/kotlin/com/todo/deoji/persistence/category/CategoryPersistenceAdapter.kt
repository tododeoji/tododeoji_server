package com.todo.deoji.persistence.category

import com.todo.deoji.core.domain.category.exception.CategoryNotFoundException
import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.category.spi.CategoryPort
import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.persistence.category.adapter.toDomain
import com.todo.deoji.persistence.category.adapter.toEntity
import com.todo.deoji.persistence.category.repository.CategoryCustomRepository
import com.todo.deoji.persistence.category.repository.CategoryJpaRepository
import com.todo.deoji.persistence.user.adapter.toEntity
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

    override fun findById(categoryId: Long): Category =
        categoryJpaRepository.findById(categoryId)
            .orElseThrow { CategoryNotFoundException() }
            .toDomain()

    override fun findByUserId(userId: String): List<Category> =
        categoryJpaRepository.findAllByUserIdOrderBySortAsc(userId)
            .map { it.toDomain() }

    override fun findAllByUserAndIsNotHideStatus(user: User, hideStatus: Boolean): List<Category> =
        categoryJpaRepository.findAllByUserAndHideStatus(user.toEntity(), hideStatus)
            .map { it.toDomain() }
}