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
                YearMonth.of(year, month).lengthOfMonth()
                    .let { totalDay ->
                        todoPort.findAllByMonthAndYearAndUser(month, year, totalDay, user)
                            .let { todoDataList ->
                                (1..totalDay)
                                    .map { day ->
                                        GetMainDataResponseDto(
                                            day = String.format("%04d-%02d-%02d", year, month, day),
                                            todoList = todoDataList
                                                .filter {
                                                    val startDay = it.startDateTime.dayOfMonth
                                                    val endDay = it.endDateTime.dayOfMonth

                                                    //시작일이 해당 월 이전이고 종료일이 해당 월이면
                                                    if (it.startDateTime.year < year || (it.startDateTime.year == year && it.startDateTime.monthValue < month)) {
                                                        day in 1..endDay
                                                    }
                                                    //시작일이 해당 월이고 종료일이 해당 월 이후면
                                                    else if (it.endDateTime.year > year || (it.endDateTime.year == year && it.endDateTime.monthValue > month)) {
                                                        day in startDay..totalDay
                                                    }
                                                    //시작일과 종료일이 모두 해당 월 안에 있으면
                                                    else {
                                                        day in startDay..endDay
                                                    }
                                                }
                                        )
                                    }
                            }
                    }
            }

}