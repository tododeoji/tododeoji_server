package com.todo.deoji.presentation.domain.todo

import com.todo.deoji.core.domain.todo.usecase.AddTodoUseCase
import com.todo.deoji.core.domain.todo.usecase.GetTodoMainListUseCase
import com.todo.deoji.core.domain.todo.usecase.GetTodoMainUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.todo.data.extension.toAddTodoRequestDto
import com.todo.deoji.presentation.domain.todo.data.extension.toGetMainTodoResponseData
import com.todo.deoji.presentation.domain.todo.data.extension.toGetTodoMainListResponseData
import com.todo.deoji.presentation.domain.todo.data.request.AddTodoRequestData
import com.todo.deoji.presentation.domain.todo.data.response.GetMainTodoResponseData
import com.todo.deoji.presentation.domain.todo.data.response.GetTodoMainListResponseData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@WebAdapter("/todo")
class TodoWebAdapter(
    private val addTodoUseCase: AddTodoUseCase,
    private val getTodoMainListUseCase: GetTodoMainListUseCase,
    private val getTodoMainUseCase: GetTodoMainUseCase
) {
    @PostMapping
    fun addTodo(@RequestBody addTodoRequestData: AddTodoRequestData): ResponseEntity<Void> =
        addTodoUseCase.execute(addTodoRequestData.toAddTodoRequestDto())
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }

    @GetMapping
    fun getTodoListByYearMonth(
        @RequestParam(name = "month") month: Int,
        @RequestParam(name = "year") year: Int
    ): ResponseEntity<GetTodoMainListResponseData> =
        ResponseEntity.ok().body(getTodoMainListUseCase.execute(month, year).toGetTodoMainListResponseData())

    @GetMapping("/list")
    fun getTodoListByYearAndMonth(
        @RequestParam(name = "month") month: Int,
        @RequestParam(name = "year") year: Int
    ): ResponseEntity<List<GetMainTodoResponseData>> =
        ResponseEntity.ok().body(getTodoMainUseCase.execute(month, year).map { it.toGetMainTodoResponseData() })

}