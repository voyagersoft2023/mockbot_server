package com.voyagersoft.mockbot.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.voyagersoft.mockbot.config.xss.HtmlCharacterEscapesBack
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val objectMapper: ObjectMapper) : WebMvcConfigurer {
    @Bean
    fun jsonEscapeConverter(): MappingJackson2HttpMessageConverter {
        val copy = objectMapper.copy()
        copy.factory.setCharacterEscapes(HtmlCharacterEscapesBack())
        return MappingJackson2HttpMessageConverter(copy)
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}
