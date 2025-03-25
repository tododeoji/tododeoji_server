package com.todo.deoji.persistence.todo.entity

import com.todo.deoji.core.domain.todo.model.TodoActiveStatus
import com.todo.deoji.persistence.category.entity.CategoryJpaEntity
import com.todo.deoji.persistence.user.entity.UserJpaEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todo")
class TodoJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    val sort: Int,
    @Enumerated(EnumType.STRING)
    val activeStatus: TodoActiveStatus,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: CategoryJpaEntity
)