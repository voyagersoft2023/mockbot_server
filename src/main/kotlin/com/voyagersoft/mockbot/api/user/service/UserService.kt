package com.voyagersoft.mockbot.api.user.service

import com.voyagersoft.mockbot.api.user.model.dto.UserResponse
import com.voyagersoft.mockbot.utils.jwt.JwtUtils
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val jwtUtils: JwtUtils,
) {

    fun register(): UserResponse {
        val response = UserResponse()
        response.accessToken = jwtUtils.generateAccessToken("holywater@voyagersoft.co.kr", Collections.singletonList("USER"))
        response.refreshToken = jwtUtils.generateRefreshToken("holywater@voyagersoft.co.kr", Collections.singletonList("USER"))
        return response
    }

}