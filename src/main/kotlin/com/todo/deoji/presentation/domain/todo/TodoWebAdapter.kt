package com.todo.deoji.presentation.domain.todo

import com.todo.deoji.core.domain.todo.usecase.AddTodoUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.todo.data.extension.toAddTodoRequestDto
import com.todo.deoji.presentation.domain.todo.data.request.AddTodoRequestData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@WebAdapter("/todo")
class TodoWebAdapter(
    private val addTodoUseCase: AddTodoUseCase
) {
    @PostMapping
    fun addTodo(@RequestBody addTodoRequestData: AddTodoRequestData): ResponseEntity<Void> =
        addTodoUseCase.execute(addTodoRequestData.toAddTodoRequestDto())
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
}