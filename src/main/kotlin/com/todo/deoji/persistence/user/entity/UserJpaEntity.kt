package com.todo.deoji.persistence.user.entity

import com.todo.deoji.core.domain.auth.model.Role
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "user")
class UserJpaEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String,
    val introduce: String?,
    val profileUrl: String?,
    val profileImgUrl: String?,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_entity", joinColumns = [JoinColumn(name = "user_id")])
    val roles: MutableList<Role>,
)