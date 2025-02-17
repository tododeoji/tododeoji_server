package com.todo.deoji.persistence.user.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "user")
class UserJpaEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val email: String,
    val introduce: String,
    val profileUrl: String,
    val profileImgUrl: String
)