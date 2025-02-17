package com.todo.deoji.persistence.user.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : CrudRepository<UserJpaRepository, String> {
}