package com.todo.deoji.persistence.user

import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.core.domain.user.spi.UserPort
import com.todo.deoji.persistence.user.adapter.toDomain
import com.todo.deoji.persistence.user.adapter.toEntity
import com.todo.deoji.persistence.user.repository.UserJpaRepository
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository
) : UserPort {
    override fun findByEmail(email: String): User? =
        userJpaRepository.findByEmail(email)?.toDomain()

    override fun findById(id: String): User? =
        userJpaRepository.findById(id).getOrNull()?.toDomain()


    override fun save(user: User): User =
        userJpaRepository.save(user.toEntity()).toDomain()
}