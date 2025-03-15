package com.todo.deoji.persistence.category.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.todo.deoji.persistence.category.entity.QCategoryJpaEntity
import com.todo.deoji.persistence.category.repository.CategoryCustomRepository
import org.springframework.stereotype.Component

@Component
class CategoryCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : CategoryCustomRepository {

    override fun findMaxSortByUserId(userId: String): Int =
        QCategoryJpaEntity.categoryJpaEntity
            .let { category ->
                jpaQueryFactory
                    .select(category.sort.max())
                    .from(category)
                    .where(category.user.id.eq(userId))
                    .fetchOne() ?: 0
            }

}