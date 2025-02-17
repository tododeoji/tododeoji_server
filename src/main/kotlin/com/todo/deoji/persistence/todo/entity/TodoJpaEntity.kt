package com.todo.deoji.persistence.todo.entity

import com.todo.deoji.persistence.category.entity.CategoryJpaEntity
import com.todo.deoji.persistence.user.entity.UserJpaEntity
import jakarta.persistence.*

@Entity
@Table(name = "todo")
class TodoJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val sort: Int,
    val colorCode: String,
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryJpaEntity
)