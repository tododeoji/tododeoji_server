package com.todo.deoji.infrastructure.global.security.auth

import com.todo.deoji.core.domain.auth.model.Role
import com.todo.deoji.core.domain.user.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuthDetailsService(
) : UserDetailsService {
    // 추후 유저네임으로 조회하는 로직으로 변경 필요
    override fun loadUserByUsername(username: String?): UserDetails =
        AuthDetails(
            User(
                id = UUID.randomUUID().toString(),
                email = "testuser@example.com",
                introduce = "안녕하세요! 저는 테스트 유저입니다.",
                profileUrl = "https://example.com/profile/testuser",
                profileImgUrl = "https://example.com/images/testuser.png",
                roles = mutableListOf(Role.USER)
            )
        )

}