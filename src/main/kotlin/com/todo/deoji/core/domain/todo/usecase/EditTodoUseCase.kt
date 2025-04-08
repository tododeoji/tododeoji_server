package com.todo.deoji.core.domain.todo.usecase

import com.todo.deoji.core.common.annotation.UseCase
import com.todo.deoji.core.domain.todo.TodoNotFoundException
import com.todo.deoji.core.domain.todo.dto.request.EditTodoRequestDto
import com.todo.deoji.core.domain.todo.spi.TodoPort

@UseCase
class EditTodoUseCase(
    private val todoPort: TodoPort
) {
    fun execute(editTodoRequestDtoList: List<EditTodoRequestDto>) {
        val todoIds = editTodoRequestDtoList.map { it.id }
        val todoList = todoPort.findAllByTodoIds(todoIds).associateBy { it.id }

        editTodoRequestDtoList
            .map { requestDto ->
                todoList[requestDto.id]?.copy(
                    name = requestDto.todoName,
                    activeStatus = requestDto.activeStatus,
                    startDateTime = requestDto.startDateTime,
                    endDateTime = requestDto.endDateTime,
                    sort = requestDto.sort
                ) ?: throw TodoNotFoundException()
            }
            .let { todoPort.saveAll(it) }
    }
}