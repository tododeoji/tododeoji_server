package com.todo.deoji.persistence.category.entity

import com.todo.deoji.persistence.user.entity.UserJpaEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "category")
class CategoryJpaEntity(
    @Id
    val id: Long,
    val name: String,
    val sort: Int,
    val colorCode: String,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserJpaEntity
)