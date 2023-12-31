package com.voyagersoft.mockbot.apis.test.controller

import com.voyagersoft.mockbot.apis.jwt.model.dto.JwtDto
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/test")
@RestController
class TestController {

    @GetMapping("/load")
    @Throws(Exception::class)
    fun load(): ResponseEntity<*> {
        try {
            val a = 0/0
        } catch (e: Exception) {
            throw Exception("error test ${e.message}")
        }

        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = "성공했슴다"
        }

        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/post/test")
    fun postTest(@RequestBody body: JwtDto) {

    }
}