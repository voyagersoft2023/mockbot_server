package com.voyagersoft.mockbot.api.user.service

import com.voyagersoft.mockbot.api.api.model.dto.response.ApiDto
import com.voyagersoft.mockbot.api.api.model.dto.response.ApiRequestDto
import com.voyagersoft.mockbot.api.api.model.entity.Api
import com.voyagersoft.mockbot.api.api.model.entity.ApiRequest
import com.voyagersoft.mockbot.api.api.repository.ApiRepository
import com.voyagersoft.mockbot.api.user.model.dto.UserRequest
import com.voyagersoft.mockbot.api.user.model.dto.UserResponse
import com.voyagersoft.mockbot.api.user.model.entity.User
import com.voyagersoft.mockbot.api.user.repository.UserRepository
import com.voyagersoft.mockbot.utils.jwt.JwtUtils
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

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