package com.todo.deoji.persistence.todo.repository.impl

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.todo.dto.response.GetMainDataTodoResponseDto
import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.persistence.category.adapter.toEntity
import com.todo.deoji.persistence.category.entity.QCategoryJpaEntity
import com.todo.deoji.persistence.todo.entity.QTodoJpaEntity
import com.todo.deoji.persistence.todo.entity.TodoJpaEntity
import com.todo.deoji.persistence.todo.repository.TodoCustomRepository
import com.todo.deoji.persistence.user.entity.QUserJpaEntity
import com.todo.deoji.persistence.user.entity.UserJpaEntity
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Component
class TodoCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
) : TodoCustomRepository {
    override fun findMaxSortByCategoryAndStartDateAndEndDate(
        category: Category,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Int =
        QTodoJpaEntity.todoJpaEntity
            .let { qTodo ->
                jpaQueryFactory
                    .select(qTodo.sort.max())
                    .from(qTodo)
                    .where(
                        qTodo.category.eq(category.toEntity()),
//                        qTodo.startDate.year().eq(startDate.year),
//                        qTodo.startDate.month().eq(startDate.monthValue),
//                        qTodo.startDate.dayOfMonth().eq(startDate.dayOfMonth),
                    )
                    .fetchOne() ?: 0
            }

    override fun findAllByMonthAndYearAndUser(
        month: Int,
        year: Int,
        lastDayOfMonth: Int,
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
                    qTodo.startDateTime,
                    qTodo.endDateTime,
                    qTodo.name,
                    qCategory.name,
                    qCategory.colorCode
                )
            )
            .from(qTodo)
            .join(qTodo.category, qCategory)
            .where(
                qCategory.hideStatus.isFalse,
                qCategory.user.eq(user),
                qTodo.startDateTime.loe(LocalDateTime.of(year, month, lastDayOfMonth, 23, 59, 59)),
                qTodo.endDateTime.goe(LocalDateTime.of(year, month, 1, 0, 0, 0))
            )
            .fetch()
    }

    override fun findAllByLocalDateAndUser(
        localDate: LocalDate,
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
                    qTodo.startDateTime,
                    qTodo.endDateTime,
                    qTodo.name,
                    qCategory.name,
                    qCategory.colorCode
                )
            )
            .from(qTodo)
            .join(qTodo.category, qCategory)
            .where(
                qCategory.hideStatus.isFalse,
                qCategory.user.eq(user),
                qTodo.startDateTime.loe(localDate.atStartOfDay()),
                qTodo.endDateTime.goe(localDate.atTime(LocalTime.MAX))
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
                        qTodo.startDateTime.year().eq(year),
                        qTodo.startDateTime.month().eq(month)
                    )
                    .fetch()
            }

    override fun findAllByTodoIds(todoIds: List<Long>): List<TodoJpaEntity> =
        QTodoJpaEntity.todoJpaEntity
            .let { qTodo ->
                jpaQueryFactory
                    .selectFrom(qTodo)
                    .where(qTodo.id.`in`(todoIds))
                    .fetch()
            }

}