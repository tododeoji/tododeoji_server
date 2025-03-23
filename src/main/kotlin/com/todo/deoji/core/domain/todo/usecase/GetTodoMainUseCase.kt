package com.todo.deoji.core.domain.todo.usecase

import com.todo.deoji.core.common.annotation.ReadOnlyUseCase
import com.todo.deoji.core.domain.todo.dto.response.GetMainDataResponseDto
import com.todo.deoji.core.domain.todo.spi.TodoPort
import com.todo.deoji.core.domain.user.service.GetCurrentUserService
import java.time.YearMonth

@ReadOnlyUseCase
class GetTodoMainUseCase(
    private val todoPort: TodoPort,
    private val getCurrentUserService: GetCurrentUserService
) {
    fun execute(month: Int, year: Int): List<GetMainDataResponseDto> =

        // 년과 월을 받아서 달에
        getCurrentUserService.getCurrentUser()
            .let { user ->
                todoPort.findAllByMonthAndYearAndUser(month, year, user)
                    .let { todoDataList ->
                        YearMonth.of(year, month).lengthOfMonth()
                            .let { totalDay ->
                                (1..totalDay)
                                    .map { day ->
                                        GetMainDataResponseDto(
                                            day = "$year-$month-$day",
                                            todoList = todoDataList
                                                .filter { it.todoDate.dayOfMonth == day }
                                        )
                                    }
                            }
                    }
            }

}