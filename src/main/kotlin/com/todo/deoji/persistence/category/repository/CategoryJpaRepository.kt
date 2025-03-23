package com.todo.deoji.persistence.category.repository

import com.todo.deoji.persistence.category.entity.CategoryJpaEntity
import com.todo.deoji.persistence.user.entity.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryJpaRepository : CrudRepository<CategoryJpaEntity, Long>{
    fun findAllByUserAndHideStatus(userJpaEntity: UserJpaEntity, hideStatus: Boolean): List<CategoryJpaEntity>
}