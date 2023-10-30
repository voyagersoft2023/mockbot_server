package com.voyagersoft.mockbot.api.user.controller

import com.voyagersoft.mockbot.api.user.service.UserService
import com.voyagersoft.mockbot.utils.jwt.JwtUtils
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
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
    fun login(): String {
        return "login"
    }

    @PostMapping("/register")
    @Throws(Exception::class)
    fun register(@RequestBody map: Map<String, String>): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = userService.register()
        }
        return ResponseEntity(response, HttpStatus.OK)
    }



}

