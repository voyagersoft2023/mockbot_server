package com.voyagersoft.mockbot.apis.social.service

import com.fasterxml.jackson.databind.JsonNode
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.voyagersoft.mockbot.apis.user.model.entity.User
import com.voyagersoft.mockbot.apis.user.repository.UserRepository
import mu.KotlinLogging
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate


@Service
class SocialLoginService(
    private val userRepository: UserRepository,
    private val env: Environment,
    private val restTemplate: RestTemplate
) {

    private val log = KotlinLogging.logger{ }
    /* 2023.10.13 [shiningtrue]: oAuth 조회된 유저 정보 세팅 */
    fun socialLogin(code: String, registrationId: String): String {
        var reslut = ""
        val accessToken = getAccessToken(code, registrationId)
        val userResourceNode: JsonNode? = getUserResource(accessToken, registrationId)
        val element: JsonElement = JsonParser.parseString((userResourceNode.toString()))

        if (userResourceNode != null) {
            var socialId = ""
//            var name = ""
//            var email = ""
            if ("google".equals(registrationId)) {
                socialId = userResourceNode["id"].asText()
//                email = userResourceNode["email"].asText()
//                name = userResourceNode["name"].asText()
            } else if ("kakao".equals(registrationId)) {
                socialId = userResourceNode["id"].asText()

//                val properties = element.getAsJsonObject()["properties"].getAsJsonObject()
//                val kakao_account = element.getAsJsonObject()["kakao_account"].getAsJsonObject()
//                email = kakao_account.getAsJsonObject()["email"].asString
//                name = properties.getAsJsonObject()["nickname"].asString
            } else if ("naver".equals(registrationId)) {
                val response = element.getAsJsonObject()["response"].getAsJsonObject()
                socialId = response.getAsJsonObject()["id"].asString
//                email = response.getAsJsonObject()["email"].asString
//                name = response.getAsJsonObject()["nickname"].asString
            }

            /* 2023.10.20 [shiningtrue]: 가입된 정보가 없다면 회원가입 페이지로 이동 */
            if (userRepository.findBySocialId(socialId) === null) {
                val user = User().also {
                    it.socialId = socialId
//                    it.email = email
//                    it.name = name
                    it.loginType = registrationId
                }
                reslut = "회원가입 필요"

                // 2023.10.20 [shiningtrue]: return을 step2로 이동
                userRepository.save(user)
            } else if (userRepository.findBySocialId(socialId) != null) {    /* 2023.10.20 [shiningtrue]: 가입된 정보가 있다면 로그인성공 */
                reslut = "로그인 성공"
            }
        }

        return reslut
    }

    /* 2023.10.13 [shiningtrue]: oAuth Token 발급 */
    @Throws(Exception::class)
    private fun getAccessToken(authorizationCode: String, registrationId: String): String {
        log.info("로깅 발생!");
        val clientId: String = env.getRequiredProperty("oauth2.$registrationId.client-id")
        val clientSecret: String = env.getRequiredProperty("oauth2.$registrationId.client-secret")
        val redirectUri: String = env.getRequiredProperty("oauth2.$registrationId.redirect-uri")
        val tokenUri: String = env.getRequiredProperty("oauth2.$registrationId.token-uri")

        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("code", authorizationCode)
        params.add("client_id", clientId)
        params.add("client_secret", clientSecret)
        params.add("redirect_uri", redirectUri)
        params.add("grant_type", "authorization_code")

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val entity = HttpEntity(params, headers)
        val responseNode = restTemplate.exchange(
            tokenUri, HttpMethod.POST, entity,
            JsonNode::class.java
        )

        return responseNode.body!!["access_token"].asText()
    }

    /* 2023.10.13 [shiningtrue]: oAuth 발급된 Token으로 유저 정보 조회 */
    private fun getUserResource(accessToken: String, registrationId: String): JsonNode? {
        val resourceUri = env.getRequiredProperty("oauth2.$registrationId.resource-uri")
        val headers = HttpHeaders()
        headers["Authorization"] = "Bearer $accessToken"
        val entity: HttpEntity<*> = HttpEntity<Any?>(headers)
        return restTemplate.exchange(
            resourceUri, HttpMethod.GET, entity,
            JsonNode::class.java
        ).body
    }

}