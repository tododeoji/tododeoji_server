package com.todo.deoji.presentation.domain.todo

import com.todo.deoji.core.domain.todo.usecase.AddTodoUseCase
import com.todo.deoji.core.domain.todo.usecase.GetStatusTodoListUseCase
import com.todo.deoji.core.domain.todo.usecase.GetTodoMainListUseCase
import com.todo.deoji.core.domain.todo.usecase.GetTodoMainUseCase
import com.todo.deoji.presentation.common.WebAdapter
import com.todo.deoji.presentation.domain.todo.data.extension.*
import com.todo.deoji.presentation.domain.todo.data.request.AddTodoRequestData
import com.todo.deoji.presentation.domain.todo.data.response.GetMainTodoResponseData
import com.todo.deoji.presentation.domain.todo.data.response.GetStatusTodoListResponseData
import com.todo.deoji.presentation.domain.todo.data.response.GetTodoMainListResponseData
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
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
    private val getTodoMainUseCase: GetTodoMainUseCase,
    private val getStatusTodoListUseCase: GetStatusTodoListUseCase
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
        @RequestParam(name = "month") @Min(1) @Max(12) month: Int,
        @RequestParam(name = "year") @Min(1) year: Int
    ): ResponseEntity<List<GetMainTodoResponseData>> =
        ResponseEntity.ok().body(getTodoMainUseCase.execute(month, year).map { it.toGetMainTodoResponseData() })

    @GetMapping("/status")
    fun getStatusTodo(): ResponseEntity<GetStatusTodoListResponseData> =
        ResponseEntity.ok().body(getStatusTodoListUseCase.execute().toGetStatusTodoListResponseData())

}