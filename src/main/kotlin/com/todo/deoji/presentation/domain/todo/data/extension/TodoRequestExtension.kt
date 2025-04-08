package com.todo.deoji.presentation.domain.todo.data.extension

import com.todo.deoji.core.domain.todo.dto.request.AddTodoRequestDto
import com.todo.deoji.core.domain.todo.dto.request.EditTodoRequestDto
import com.todo.deoji.presentation.domain.todo.data.request.AddTodoRequestData
import com.todo.deoji.presentation.domain.todo.data.request.EditTodoRequestData

fun AddTodoRequestData.toAddTodoRequestDto(): AddTodoRequestDto =
    AddTodoRequestDto(
        todoName = this.todoName,
        activeStatus = this.activeStatus,
        startDateTime = this.startDateTime,
        endDateTime = this.endDateTime,
        categoryId = this.categoryId
    )

fun EditTodoRequestData.toEditTodoRequestDtoList(): List<EditTodoRequestDto> =
    this.todoList
        .map {
            EditTodoRequestDto(
                id = it.id,
                activeStatus = it.activeStatus,
                todoName = it.todoName,
                startDateTime = it.startDateTime,
                endDateTime = it.endDateTime,
                sort = it.sort
            )
        }