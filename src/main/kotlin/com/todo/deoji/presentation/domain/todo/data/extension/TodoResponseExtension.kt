package com.todo.deoji.presentation.domain.todo.data.extension

import com.todo.deoji.core.domain.todo.dto.response.GetMainDataResponseDto
import com.todo.deoji.core.domain.todo.dto.response.GetMainListResponseDto
import com.todo.deoji.core.domain.todo.dto.response.GetStatusTodoListResponseDto
import com.todo.deoji.presentation.domain.todo.data.response.GetMainTodoResponseData
import com.todo.deoji.presentation.domain.todo.data.response.GetStatusTodoListResponseData
import com.todo.deoji.presentation.domain.todo.data.response.GetTodoMainListResponseData

fun GetMainListResponseDto.toGetTodoMainListResponseData(): GetTodoMainListResponseData =
    GetTodoMainListResponseData(
        month = this.month,
        year = this.year,
        dayDataList = this.dayDataList
    )

fun GetMainDataResponseDto.toGetMainTodoResponseData(): GetMainTodoResponseData =
    GetMainTodoResponseData(
        day = this.day,
        todoDataList = this.todoList
    )

fun GetStatusTodoListResponseDto.toGetStatusTodoListResponseData(): GetStatusTodoListResponseData =
    GetStatusTodoListResponseData(
        todo = this.todo,
        inProgress = this.inProgress,
        completed = this.completed
    )