package cz.schrek.tuzex.application.spring.security

import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
//    private val jwtTokenFilter: JwtTokenFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain = http {
        cors {}
        csrf { disable() }

        sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }

        exceptionHandling {
            authenticationEntryPoint = AuthenticationEntryPoint { _, response, authException ->
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.message)
            }
        }

        authorizeHttpRequests {
            authorize("/api/auth/v1/login", permitAll)
            authorize(anyRequest, authenticated)
        }

//        addFilterBefore<UsernamePasswordAuthenticationFilter>(jwtTokenFilter)
    }.let { http.build() }

    @Bean
    fun corsFilter(): CorsFilter = CorsFilter(
        UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", CorsConfiguration().apply {
                allowCredentials = true
                addAllowedOrigin("*")
                addAllowedHeader("*")
                addAllowedMethod("*")
            })
        }
    )

}
