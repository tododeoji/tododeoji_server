package com.todo.deoji.persistence.todo.repository.impl

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.todo.dto.response.GetMainDataTodoResponseDto
import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import com.todo.deoji.persistence.category.adapter.toEntity
import com.todo.deoji.persistence.category.entity.QCategoryJpaEntity
import com.todo.deoji.persistence.todo.entity.QTodoJpaEntity
import com.todo.deoji.persistence.todo.entity.TodoJpaEntity
import com.todo.deoji.persistence.todo.repository.TodoCustomRepository
import com.todo.deoji.persistence.user.entity.QUserJpaEntity
import com.todo.deoji.persistence.user.entity.UserJpaEntity
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class TodoCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : TodoCustomRepository {
    override fun findMaxSortByCategoryAndRunDate(category: Category, runDate: LocalDateTime): Int =
        QTodoJpaEntity.todoJpaEntity
            .let { qTodo ->
                jpaQueryFactory
                    .select(qTodo.sort.max())
                    .from(qTodo)
                    .where(
                        qTodo.category.eq(category.toEntity()),
                        qTodo.runDate.year().eq(runDate.year),
                        qTodo.runDate.month().eq(runDate.monthValue),
                        qTodo.runDate.dayOfMonth().eq(runDate.dayOfMonth)
                    )
                    .fetchOne() ?: 0
            }

    override fun findAllByMonthAndYearAndUser(
        month: Int,
        year: Int,
        user: UserJpaEntity
    ): List<GetMainDataTodoResponseDto> {
        val qTodo = QTodoJpaEntity.todoJpaEntity
        val qCategory = QCategoryJpaEntity.categoryJpaEntity

        return jpaQueryFactory
            .select(
                Projections.constructor(
                    GetMainDataTodoResponseDto::class.java,
                    qTodo.id,
                    qTodo.activeStatus,
                    qTodo.runDate,
                    qTodo.name,
                    qCategory.name,
                    qCategory.colorCode
                )
            )
            .from(qTodo)
            .join(qTodo.category, qCategory)
            .on(
                qCategory.hideStatus.isFalse,
                qCategory.user.eq(user)
            )
            .where(
                qTodo.runDate.year().eq(year),
                qTodo.runDate.month().eq(month)
            )
            .fetch()
    }

    override fun findAllByCategoryAndMonthAndYear(categoryIds: List<Long>, month: Int, year: Int): List<TodoJpaEntity> =
        QTodoJpaEntity.todoJpaEntity
            .let { qTodo ->
                jpaQueryFactory
                    .selectFrom(qTodo)
                    .where(
                        qTodo.category.id.`in`(categoryIds),
                        qTodo.runDate.year().eq(year),
                        qTodo.runDate.month().eq(month)
                    )
                    .fetch()
            }
}