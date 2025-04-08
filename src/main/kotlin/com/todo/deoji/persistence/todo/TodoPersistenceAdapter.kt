package com.todo.deoji.persistence.todo

import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.todo.dto.response.GetMainDataTodoResponseDto
import com.todo.deoji.core.domain.todo.model.Todo
import com.todo.deoji.core.domain.todo.spi.TodoPort
import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.persistence.todo.adapter.toDomain
import com.todo.deoji.persistence.todo.adapter.toEntity
import com.todo.deoji.persistence.todo.repository.TodoCustomRepository
import com.todo.deoji.persistence.todo.repository.TodoJpaRepository
import com.todo.deoji.persistence.user.adapter.toEntity
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime

@Component
class TodoPersistenceAdapter(
    private val todoJpaRepository: TodoJpaRepository,
    private val todoCustomRepository: TodoCustomRepository
) : TodoPort {
    override fun save(todo: Todo): Todo =
        todoJpaRepository.save(todo.toEntity()).toDomain()

    override fun saveAll(todoList: List<Todo>) {
        todoJpaRepository.saveAll(todoList.map { it.toEntity() })
    }

    override fun findMaxSortByCategoryAndStartDateAndEndDate(
        category: Category,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Int =
        todoCustomRepository.findMaxSortByCategoryAndStartDateAndEndDate(category, startDate, endDate)

    override fun findAllByMonthAndYearAndUser(
        month: Int, year: Int, lastDayOfMonth: Int,
        user: User
    ): List<GetMainDataTodoResponseDto> =
        todoCustomRepository.findAllByMonthAndYearAndUser(month, year, lastDayOfMonth, user.toEntity())

    override fun findAllByLocalDateAndUser(localDate: LocalDate, user: User): List<GetMainDataTodoResponseDto> =
        todoCustomRepository.findAllByLocalDateAndUser(localDate, user.toEntity())

    override fun findAllByCategoryIdsAndMonthAndYear(
        categoryIds: List<Long>,
        month: Int,
        year: Int
    ): List<Todo> =
        todoCustomRepository.findAllByCategoryAndMonthAndYear(categoryIds, month, year)
            .map { it.toDomain() }

    override fun findAllByTodoIds(todoIds: List<Long>): List<Todo> =
        todoCustomRepository.findAllByTodoIds(todoIds).map { it.toDomain() }

}