package com.todo.deoji.persistence.user.repository

import com.todo.deoji.persistence.user.entity.UserJpaEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : CrudRepository<UserJpaEntity, String> {
    fun findByEmail(email: String): UserJpaEntity?
}