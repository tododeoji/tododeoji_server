package com.todo.deoji.core.domain.todo.usecase

import com.todo.deoji.core.common.annotation.ReadOnlyUseCase
import com.todo.deoji.core.domain.todo.dto.response.GetStatusTodoListResponseDto
import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import com.todo.deoji.core.domain.todo.spi.TodoPort
import com.todo.deoji.core.domain.user.service.GetCurrentUserService
import java.time.LocalDate

@ReadOnlyUseCase
class GetStatusTodoListUseCase(
    private val todoPort: TodoPort,
    private val currentUserService: GetCurrentUserService
) {
    fun execute(): GetStatusTodoListResponseDto =
        currentUserService.getCurrentUser()
            .let { user ->
                val localDate = LocalDate.now()
                val todoList = todoPort.findAllByLocalDateAndUser(localDate, user)

                GetStatusTodoListResponseDto(
                    todo = todoList.filter { it.activeStatus == TodoActiveStatus.TODO },
                    inProgress = todoList.filter { it.activeStatus == TodoActiveStatus.IN_PROGRESS },
                    completed = todoList.filter { it.activeStatus == TodoActiveStatus.COMPLETED }
                )
            }

}