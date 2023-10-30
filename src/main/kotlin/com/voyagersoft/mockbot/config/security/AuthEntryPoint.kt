package com.voyagersoft.mockbot.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.voyagersoft.mockbot.config.exception.ExceptionResponse
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.redis.connection.ReactiveStreamCommands.AddStreamRecord.body
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*
import javax.naming.AuthenticationNotSupportedException


@Component
class AuthEntryPoint: AuthenticationEntryPoint {

    @Throws(Exception::class)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response!!.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_FORBIDDEN

        val structure = ResponseStructure().apply {
            code = ResponseCode.FORBIDDEN.code
            message = ResponseCode.FORBIDDEN.message
            data = ExceptionResponse(
                SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Date()),
                authException?.message ?: "인증 에러 발생",
                request?.requestURI ?: ""
            )
        }

        val mapper = ObjectMapper()
        mapper.writeValue(response.outputStream, structure)
    }
}