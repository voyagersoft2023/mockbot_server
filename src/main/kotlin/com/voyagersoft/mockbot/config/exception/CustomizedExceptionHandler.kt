package com.voyagersoft.mockbot.config.exception

import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import io.jsonwebtoken.JwtException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.text.SimpleDateFormat
import java.util.*
import javax.naming.AuthenticationNotSupportedException

@RestController
@ControllerAdvice
class CustomizedExceptionHandler: ResponseEntityExceptionHandler() {
    val log = KotlinLogging.logger{ }

    @ExceptionHandler(Exception::class)
    fun exception(exception: Exception, request: WebRequest): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.ERROR.code
            message = ResponseCode.ERROR.message
            data = ExceptionResponse(
                SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Date()),
                exception.message!!,
                request.getDescription(false)
            )
        }

        log.error("time: ${(response.data as ExceptionResponse).date}")
        log.error("message: ${(response.data as ExceptionResponse).message}")
        log.error("content: ${(response.data as ExceptionResponse).content}")

        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(JwtException::class)
    fun jwtException(exception: Exception, request: WebRequest): ResponseEntity<*> {
        val response = ResponseStructure().apply {
            code = ResponseCode.TOKEN_ERROR.code
            message = ResponseCode.TOKEN_ERROR.message
            data = ExceptionResponse(
                SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Date()),
                exception.message!!,
                request.getDescription(false)
            )
        }

        log.error("time: ${(response.data as ExceptionResponse).date}")
        log.error("message: ${(response.data as ExceptionResponse).message}")
        log.error("content: ${(response.data as ExceptionResponse).content}")

        return ResponseEntity(response, HttpStatus.UNAUTHORIZED)
    }

}