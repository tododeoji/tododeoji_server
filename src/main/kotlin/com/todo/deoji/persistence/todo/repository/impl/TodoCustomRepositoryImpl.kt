package com.todo.deoji.persistence.todo.repository.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.persistence.category.adapter.toEntity
import com.todo.deoji.persistence.todo.entity.QTodoJpaEntity
import com.todo.deoji.persistence.todo.repository.TodoCustomRepository
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


}