package com.voyagersoft.mockbot.apis.api.controller

import com.voyagersoft.mockbot.apis.api.model.dto.request.ApiDto
import com.voyagersoft.mockbot.apis.api.service.ApiService
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.*

@RequestMapping("/api")
@RestController
class ApiController(
    private val apiService: ApiService,
) {

    /* 2023.11.28 [shiningtrue] : API 조회 */
    @PostMapping("/api")
    @Throws(Exception::class)
    fun loadApi(@RequestBody request: ApiDto): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = apiService.loadApi(request.projectId)
        }
        return ResponseEntity(response, HttpStatus.OK)
    }

    /* 2023.11.28 [shiningtrue] : API 하위 Request 조회 */
    @PostMapping("/apiRequest")
    @Throws(Exception::class)
    fun loadApiRequest(): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = apiService.loadApiRequest()
        }
        return ResponseEntity(response, HttpStatus.OK)
    }

    /* 2023.11.28 [shiningtrue] : API 하위 Response 조회 */
    @PostMapping("/apiResponse")
    @Throws(Exception::class)
    fun loadApiResponse(): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.SUCCESS.code
            message = ResponseCode.SUCCESS.message
            data = apiService.loadApiResponse()
        }
        return ResponseEntity(response, HttpStatus.OK)
    }
}

