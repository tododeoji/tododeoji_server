package com.todo.deoji.persistence.user.adapter

import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.persistence.user.entity.UserJpaEntity

fun UserJpaEntity.toDomain() =
    User(
        id = this.id,
        email = this.email,
        introduce = this.introduce,
        profileUrl = this.profileUrl,
        profileImgUrl = this.profileImgUrl,
        roles = this.roles
    )

fun User.toEntity() =
    UserJpaEntity(
        id = this.id,
        email = this.email,
        introduce = this.introduce,
        profileUrl = this.profileUrl,
        profileImgUrl = this.profileImgUrl,
        roles = this.roles
    )