package com.todo.deoji.infrastructure.global.oauth.data

import com.todo.deoji.core.domain.auth.model.Role
import com.todo.deoji.core.domain.user.model.User

class OAuth2UserInfo private constructor(
    val name: String,
    val email: String,
    val profile: String?
) {
    companion object {
        fun of(registrationId: String?, attributes: Map<String, Any>): OAuth2UserInfo =
            when (registrationId) {
                "google" -> attributes.toGoogleUserInfo()
                "kakao" -> attributes.toKakaoUserInfo()
                "naver" -> attributes.toNaverUserInfo()
                else -> throw IllegalArgumentException("지원하지 않는 OAuth 제공자: $registrationId")
            }

        private fun Map<String, Any>.toGoogleUserInfo() = OAuth2UserInfo(
            name = this["name"] as? String ?: throw IllegalArgumentException("Google 유저 이름 없음"),
            email = this["email"] as? String ?: throw IllegalArgumentException("Google 이메일 없음"),
            profile = this["picture"] as? String
        )

        private fun Map<String, Any>.toKakaoUserInfo(): OAuth2UserInfo =
            (this["kakao_account"] as? Map<*, *>)?.let { account ->
                (account["profile"] as? Map<*, *>)?.let { profile ->
                    OAuth2UserInfo(
                        name = profile["nickname"] as? String ?: "Unknown",
                        email = account["email"] as? String ?: throw IllegalArgumentException("Kakao 이메일 없음"),
                        profile = profile["profile_image_url"] as? String
                    )
                }
            } ?: throw IllegalArgumentException("카카오 에러")

        private fun Map<String, Any>.toNaverUserInfo(): OAuth2UserInfo =
            (this["response"] as? Map<*, *>)
                ?.filterKeys { it is String }
                ?.mapKeys { it.key as String }
                ?.let {
                    OAuth2UserInfo(
                        name = it["name"] as? String ?: "Unknown",
                        email = it["email"] as? String ?: throw IllegalArgumentException("Naver 이메일 없음"),
                        profile = it["profile_image"] as? String
                    )
                } ?: throw IllegalArgumentException("Naver 에러")

    }

    fun toEntity(): User = User(
        name = name,
        email = email,
        profileImgUrl = profile,
        roles = mutableListOf(Role.USER),
        introduce = null,
        profileUrl = null
    )
}
