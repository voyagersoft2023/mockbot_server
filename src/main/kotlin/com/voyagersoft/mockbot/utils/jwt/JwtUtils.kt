package com.voyagersoft.mockbot.utils.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.voyagersoft.mockbot.config.exception.ExceptionResponse
import com.voyagersoft.mockbot.utils.response.ResponseCode
import com.voyagersoft.mockbot.utils.response.ResponseStructure
import io.jsonwebtoken.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JwtUtils(
    val objectMapper: ObjectMapper
) {

    private val log = KotlinLogging.logger{ }
    private val secret = "holywater"

    fun getToken(httpServletRequest: HttpServletRequest): String {
        val bearerToken = httpServletRequest.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else ""
    }

    @Throws(Exception::class)
    fun validateToken(httpServletRequest: HttpServletRequest, response: HttpServletResponse): Boolean {
        try {
            val token = getToken(httpServletRequest)
            if (token != "") {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                return true
            }
        } catch (e: SignatureException) {
            throwException(response, e.message ?: "토큰 키 오류", httpServletRequest.requestURI)
        } catch (e: MalformedJwtException) {
            throwException(response, e.message ?: "토큰 오류", httpServletRequest.requestURI)
        } catch (e: ExpiredJwtException) {
            throwException(response, e.message ?: "토큰 기간 만료", httpServletRequest.requestURI)
        } catch (e: UnsupportedJwtException) {
            throwException(response, e.message ?: "토큰 오류", httpServletRequest.requestURI)
        } catch (e: IllegalArgumentException) {
            throwException(response, e.message ?: "토큰 오류", httpServletRequest.requestURI)
        } catch (e: Exception) {
            response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
            throwException(response, e.message ?: "토큰 오류", httpServletRequest.requestURI)
        }
        return false
    }

    @Throws(Exception::class)
    fun generateAccessToken(email: String, roles: List<String>): String {
        return "Bearer ${Jwts.builder().setSubject(email).claim("role", roles).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
            .signWith(SignatureAlgorithm.HS512, secret).compact()}"
    }

    @Throws(Exception::class)
    fun generateRefreshToken(email: String, roles: List<String>): String {
        return "Bearer ${Jwts.builder().setSubject(email).claim("role", roles).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
            .signWith(SignatureAlgorithm.HS512, secret).compact()}"
    }

    fun throwException(response: HttpServletResponse, errorMessage: String, requestURI: String) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_FORBIDDEN

        val structure = ResponseStructure().apply {
            code = ResponseCode.TOKEN_ERROR.code
            message = ResponseCode.TOKEN_ERROR.message
            data = ExceptionResponse(
                SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(Date()),
                errorMessage,
                requestURI
            )
        }

        objectMapper.writeValue(response.outputStream, structure)
    }

}