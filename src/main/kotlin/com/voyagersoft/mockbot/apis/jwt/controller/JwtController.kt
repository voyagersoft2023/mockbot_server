package com.voyagersoft.mockbot.apis.jwt.controller

import com.voyagersoft.mockbot.apis.jwt.service.JwtService
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/jwt")
@RestController
class JwtController(
    val jwtService: JwtService
) {

    @PostMapping("/refresh")
    @Throws(Exception::class)
    fun refreshToken(): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = jwtService.refreshToken()
        }
        return ResponseEntity(response, HttpStatus.OK)
    }

}