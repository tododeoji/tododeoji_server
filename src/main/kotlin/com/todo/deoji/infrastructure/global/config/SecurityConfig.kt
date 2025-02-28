package com.todo.deoji.infrastructure.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.todo.deoji.infrastructure.global.jwt.adapter.ParseTokenAdapter
import com.todo.deoji.infrastructure.global.security.CustomAccessDeniedHandler
import com.todo.deoji.infrastructure.global.security.CustomAuthenticationEntryPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val parseTokenAdapter: ParseTokenAdapter,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler
) {
    @Bean
    protected fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }

        http
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

        http
            .authorizeHttpRequests {
                it.anyRequest().denyAll()
            }

        FilterConfig(objectMapper, parseTokenAdapter)

        http
            .exceptionHandling {
                it.authenticationEntryPoint(CustomAuthenticationEntryPoint(objectMapper))
                it.accessDeniedHandler(customAccessDeniedHandler)
            }

        return http.build()
    }
}