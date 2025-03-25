package com.todo.deoji.core.domain.todo.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.category.spi.CategoryPort
import com.todo.deoji.core.domain.todo.dto.request.AddTodoRequestDto
import com.todo.deoji.core.domain.todo.model.Todo
import com.todo.deoji.core.domain.todo.spi.TodoPort

@UseCase
class AddTodoUseCase(
    private val todoPort: TodoPort,
    private val categoryPort: CategoryPort
) {
    fun execute(addTodoRequestDto: AddTodoRequestDto) =
        categoryPort.findById(addTodoRequestDto.categoryId)
            .let { category ->
                todoPort.save(
                    Todo(
                        id = 0,
                        name = addTodoRequestDto.todoName,
                        activeStatus = addTodoRequestDto.activeStatus,
                        startDateTime = addTodoRequestDto.startDateTime,
                        endDateTime = addTodoRequestDto.endDateTime,
                        sort = todoPort.findMaxSortByCategoryAndStartDateAndEndDate(
                            category,
                            addTodoRequestDto.startDateTime,
                            addTodoRequestDto.endDateTime
                        ) + 1,
                        category = category
                    )
                )
            }
}