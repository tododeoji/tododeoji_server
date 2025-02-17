package com.todo.deoji.persistence.category.repository

import com.todo.deoji.persistence.category.entity.CategoryJpaEntity
import org.springframework.data.repository.CrudRepository

interface CategoryJpaRepository : CrudRepository<CategoryJpaEntity, Long>{
}