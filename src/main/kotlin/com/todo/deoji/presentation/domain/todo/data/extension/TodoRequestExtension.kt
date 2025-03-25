package com.todo.deoji.presentation.domain.todo.data.extension

import com.todo.deoji.core.domain.todo.dto.request.AddTodoRequestDto
import com.todo.deoji.presentation.domain.todo.data.request.AddTodoRequestData

fun AddTodoRequestData.toAddTodoRequestDto(): AddTodoRequestDto =
    AddTodoRequestDto(
        todoName = this.todoName,
        activeStatus = this.activeStatus,
        startDateTime = this.startDateTime,
        endDateTime = this.endDateTime,
        categoryId = this.categoryId
    )