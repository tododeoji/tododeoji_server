package com.todo.deoji.persistence.todo.repository

import com.todo.deoji.persistence.todo.entity.TodoJpaEntity
import org.springframework.data.repository.CrudRepository

interface TodoJpaRepository : CrudRepository<TodoJpaEntity, Long> {
}