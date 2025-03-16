package com.todo.deoji.core.domain.todo.model

enum class TodoActiveStatus(
    val description: String
) {
    TODO("할 일"),
    IN_PROGRESS("하는 중"),
    COMPLETED("완료")
}