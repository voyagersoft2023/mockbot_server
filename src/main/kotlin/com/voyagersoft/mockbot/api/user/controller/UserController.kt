package com.voyagersoft.mockbot.api.user.controller

import com.voyagersoft.mockbot.api.user.model.dto.UserRequest
import com.voyagersoft.mockbot.api.user.service.UserService
import com.voyagersoft.mockbot.utils.jwt.JwtUtils
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import io.jsonwebtoken.JwtException
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService,
    private val jwtUtils: JwtUtils
) {

    @PostMapping("/login")
    @Throws(Exception::class)
    fun login(
        @RequestBody request: UserRequest,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<ResponseStructure> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = userService.login()
        }
        return ResponseEntity.ok(response)
    }

    @PostMapping("/register")
    @Throws(Exception::class)
    fun register(@RequestBody request: UserRequest): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = userService.register(request)
        }
        return ResponseEntity(response, HttpStatus.OK)
    }

}

