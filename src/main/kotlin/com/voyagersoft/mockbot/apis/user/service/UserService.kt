package com.voyagersoft.mockbot.apis.user.service

import com.voyagersoft.mockbot.apis.api.repository.ApiRepository
import com.voyagersoft.mockbot.apis.user.model.dto.UserRequest
import com.voyagersoft.mockbot.apis.user.model.dto.UserResponse
import com.voyagersoft.mockbot.apis.user.model.entity.User
import com.voyagersoft.mockbot.apis.user.repository.UserRepository
import com.voyagersoft.mockbot.utils.jwt.JwtUtils
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val jwtUtils: JwtUtils,
    private val userRepository: UserRepository,
    private val apiRepository: ApiRepository,
    private val modelMapper: ModelMapper
) {

    fun login(): UserResponse {
        val response = UserResponse()
        response.accessToken = jwtUtils.generateAccessToken("holywater@voyagersoft.co.kr", Collections.singletonList("USER"))
        response.refreshToken = jwtUtils.generateRefreshToken("holywater@voyagersoft.co.kr", Collections.singletonList("USER"))
        return response
    }

    fun register(request: UserRequest): UserResponse {
        val response = UserResponse()

        request.let {
            userRepository.save(modelMapper.map(request, User::class.java))
        }
        return response
    }
}