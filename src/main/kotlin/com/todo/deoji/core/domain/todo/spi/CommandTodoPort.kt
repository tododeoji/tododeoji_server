package com.todo.deoji.core.domain.todo.spi

import com.todo.deoji.core.domain.todo.model.Todo

interface CommandTodoPort {
    fun save(todo: Todo): Todo
}