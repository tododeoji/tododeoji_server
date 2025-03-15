package com.todo.deoji.persistence.category.repository

import org.springframework.stereotype.Repository

@Repository
interface CategoryCustomRepository {
    fun findMaxSortByUserId(userId: String): Int
}