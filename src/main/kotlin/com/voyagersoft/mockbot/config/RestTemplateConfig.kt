package com.voyagersoft.mockbot.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.*

@Configuration
class RestTemplateConfig : ClientHttpRequestInterceptor {

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        TODO("Not yet implemented")
    }

    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }


}