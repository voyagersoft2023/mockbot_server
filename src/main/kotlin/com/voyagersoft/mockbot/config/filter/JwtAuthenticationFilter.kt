package com.voyagersoft.mockbot.config.filter

import com.voyagersoft.mockbot.utils.jwt.JwtUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtUtils: JwtUtils
): OncePerRequestFilter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // 2023.10.13[holywater]: JWT 체크 로직
        if (jwtUtils.validateToken(request, response)) {
            val authentication =
                UsernamePasswordAuthenticationToken("holywater", null, null)
            SecurityContextHolder.getContext().authentication = authentication
        } else {

        }

        doFilter(request, response, filterChain)
    }
}