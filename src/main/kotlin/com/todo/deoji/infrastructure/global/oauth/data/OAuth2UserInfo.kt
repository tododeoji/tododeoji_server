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

        private fun Map<String, Any>.toKakaoUserInfo(): OAuth2UserInfo {
            val account = this["kakao_account"] as? Map<*, *> ?: emptyMap<String, Any>()
            val profile = account["profile"] as? Map<*, *> ?: emptyMap<String, Any>()

            return OAuth2UserInfo(
                name = profile["nickname"] as? String ?: "Unknown",
                email = account["email"] as? String ?: throw IllegalArgumentException("Kakao 이메일 없음"),
                profile = profile["profile_image_url"] as? String
            )
        }

        private fun Map<String, Any>.toNaverUserInfo(): OAuth2UserInfo {
            val response = this["response"] as? Map<*, *> ?: emptyMap<String, Any>()
            return OAuth2UserInfo(
                name = response["name"] as? String ?: "Unknown",
                email = response["email"] as? String ?: throw IllegalArgumentException("Naver 이메일 없음"),
                profile = response["profile_image"] as? String
            )
        }
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
