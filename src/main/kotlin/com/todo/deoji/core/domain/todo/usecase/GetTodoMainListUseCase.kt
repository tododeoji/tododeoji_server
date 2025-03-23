package com.todo.deoji.core.domain.todo.usecase

import com.todo.deoji.core.common.annotation.ReadOnlyUseCase
import com.todo.deoji.core.domain.category.model.Category
import com.todo.deoji.core.domain.category.spi.CategoryPort
import com.todo.deoji.core.domain.todo.dto.response.GetCategoryMainDataResponseDto
import com.todo.deoji.core.domain.todo.dto.response.GetDayDataResponseDto
import com.todo.deoji.core.domain.todo.dto.response.GetTodoDataResponseDto
import com.todo.deoji.core.domain.todo.dto.response.GetMainListResponseDto
import com.todo.deoji.core.domain.todo.model.Todo
import com.todo.deoji.core.domain.todo.spi.TodoPort
import com.todo.deoji.core.domain.user.service.GetCurrentUserService
import java.time.YearMonth

@ReadOnlyUseCase
class GetTodoMainListUseCase(
    private val todoPort: TodoPort,
    private val categoryPort: CategoryPort,
    private val currentUserService: GetCurrentUserService
) {
    fun execute(month: Int, year: Int): GetMainListResponseDto {
        val dayCount = YearMonth.of(year, month).lengthOfMonth()

        val currentUser = currentUserService.getCurrentUser()

        val categoryList = categoryPort.findAllByUserAndIsNotHideStatus(currentUser, false)
        val todoByCategory = getTodoByCategory(categoryList, month, year)

        return GetMainListResponseDto(
            month = month,
            year = year,
            dayDataList = createDayDataList(dayCount, categoryList, todoByCategory)
        )
    }

    // 모든 투두를 한 번만 조회한 후 categoryId 기준으로 그룹화
    private fun getTodoByCategory(categoryList: List<Category>, month: Int, year: Int): Map<Long, List<Todo>> = todoPort
        .findAllByCategoryIdsAndMonthAndYear(categoryList.map { it.id }, month, year)
        .groupBy { it.category.id }

    private fun createDayDataList(
        dayCount: Int,
        categoryList: List<Category>,
        todoByCategory: Map<Long, List<Todo>>
    ): List<GetDayDataResponseDto> =
        (1..dayCount).map { day ->
            GetDayDataResponseDto(
                day = day,
                categoryDataList = categoryList.mapNotNull { category ->
                    // 현재 날짜(day)에 해당하는 todo만 필터링
                    val groupedTodos = todoByCategory[category.id]
                        ?.filter { it.runDate.dayOfMonth == day }
                        .orEmpty()

                    // 만약 해당 날짜에 해당하는 투두가 없으면 null 처리 (mapNotNull에서 자동 제거됨)
                    if (groupedTodos.isEmpty()) return@mapNotNull null

                    GetCategoryMainDataResponseDto(
                        categoryId = category.id,
                        categoryName = category.name,
                        colorCode = category.colorCode,
                        todoDataList = groupedTodos.map { todo ->
                            GetTodoDataResponseDto(
                                todoId = todo.id,
                                name = todo.name,
                                todoActiveStatus = todo.activeStatus
                            )
                        }
                    )
                }
            )

        }
}