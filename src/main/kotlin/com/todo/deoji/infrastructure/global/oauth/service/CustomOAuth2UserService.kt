package com.todo.deoji.infrastructure.global.oauth.service

import com.todo.deoji.core.domain.user.model.User
import com.todo.deoji.core.domain.user.spi.UserPort
import com.todo.deoji.infrastructure.global.oauth.data.OAuth2UserInfo
import com.todo.deoji.infrastructure.global.security.auth.AuthDetails
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
@Transactional(rollbackFor = [Exception::class])
class CustomOAuth2UserService(
    private val userPort: UserPort
) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2UserAttributes: MutableMap<String, Any> = super.loadUser(userRequest).attributes
        val registrationId = userRequest.clientRegistration.registrationId
        val userNameAttributeName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
        val oAuth2User = OAuth2UserInfo.of(registrationId, oAuth2UserAttributes)
        val user = getOrSave(oAuth2User)

        return AuthDetails(user, oAuth2UserAttributes, userNameAttributeName)
    }

    fun getOrSave(oAuth2UserInfo: OAuth2UserInfo): User =
        userPort.findByEmail(oAuth2UserInfo.email)
            ?: userPort.save(oAuth2UserInfo.toEntity())

}