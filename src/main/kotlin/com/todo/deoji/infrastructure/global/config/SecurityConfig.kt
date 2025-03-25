package com.todo.deoji.infrastructure.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.todo.deoji.infrastructure.global.jwt.adapter.ParseTokenAdapter
import com.todo.deoji.infrastructure.global.oauth.handler.CustomOAuth2FailureHandler
import com.todo.deoji.infrastructure.global.oauth.handler.OAuth2SuccessHandler
import com.todo.deoji.infrastructure.global.oauth.service.CustomOAuth2UserService
import com.todo.deoji.infrastructure.global.security.CustomAccessDeniedHandler
import com.todo.deoji.infrastructure.global.security.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val parseTokenAdapter: ParseTokenAdapter,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
    private val customOAuth2SuccessHandler: OAuth2SuccessHandler,
    private val customOAuth2UserService: CustomOAuth2UserService,
    private val customOAuth2FailureHandler: CustomOAuth2FailureHandler
) {
    @Bean
    protected fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }

        http
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        http
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.GET, "/auth/login").permitAll()

                it.requestMatchers(HttpMethod.GET, "/favicon.ico").permitAll()

                it.requestMatchers(HttpMethod.POST, "/category").authenticated()

                it.requestMatchers(HttpMethod.POST, "/todo").authenticated()
                it.requestMatchers(HttpMethod.GET, "/todo/list").authenticated()

                it.requestMatchers(HttpMethod.GET, "/user/my").authenticated()

                it.anyRequest().denyAll()
            }

        http
            .oauth2Login { oauth ->
                oauth.userInfoEndpoint { c ->
                    c.userService(customOAuth2UserService)
                }
                    .successHandler(customOAuth2SuccessHandler)
                    .failureHandler(customOAuth2FailureHandler)

            }

        FilterConfig(objectMapper, parseTokenAdapter).configure(http)

        http
            .exceptionHandling {
                it.authenticationEntryPoint(CustomAuthenticationEntryPoint(objectMapper))
                it.accessDeniedHandler(customAccessDeniedHandler)
            }

        return http.build()
    }
}