package com.todo.deoji.persistence.category.entity

import com.todo.deoji.persistence.user.entity.UserJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val sort: Int,
    val colorCode: String,
    val hideStatus: Boolean,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserJpaEntity
)