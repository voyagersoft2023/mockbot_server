package com.voyagersoft.mockbot.config.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.pool2.impl.GenericObjectPoolConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisClusterConfiguration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import java.util.*


@Configuration
class RedisConfig {

    private val hosts = mutableListOf(
        "127.0.0.1:7000",
        "127.0.0.1:7001",
        "127.0.0.1:7002",
    )
    private val connectTimeout = 5000
    private val maxTotal = 50
    private val minIdle = 10
    private val maxIdle = 30
    private val testOnBorrow = false
    private val testOnReturn = false

    @Bean
    @Throws(java.lang.Exception::class)
    fun redisTemplate(): RedisTemplate<Any, Any> {
        val redisTemplate: RedisTemplate<Any, Any> = RedisTemplate<Any, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = GenericJackson2JsonRedisSerializer(ObjectMapper())
        redisTemplate.setEnableTransactionSupport(true)
        return redisTemplate
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return lettuce()
    }

    @Throws(Exception::class)
    private fun lettuce(): RedisConnectionFactory {
        // 2023.10.17[holywater]: config
        val genericObjectPoolConfig: GenericObjectPoolConfig<*> = GenericObjectPoolConfig<Any?>()
        genericObjectPoolConfig.maxTotal = maxTotal
        genericObjectPoolConfig.minIdle = minIdle
        genericObjectPoolConfig.maxIdle = maxIdle
        genericObjectPoolConfig.testOnBorrow = testOnBorrow
        genericObjectPoolConfig.testOnReturn = testOnReturn

        // 2023.10.17[holywater]: poolingClientConfiguration
        val lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
            .poolConfig(genericObjectPoolConfig)
            .commandTimeout(Duration.ofMillis(connectTimeout.toLong()))
            .shutdownTimeout(Duration.ofMillis(connectTimeout.toLong()))
            .build()

        // 2023.10.17[holywater]: hosts 개수 처리
        return if (hosts.size == 1) {
            LettuceConnectionFactory(redisStandaloneConfiguration(), lettucePoolingClientConfiguration)
        } else {
            LettuceConnectionFactory(RedisClusterConfiguration(hosts), lettucePoolingClientConfiguration)
        }
    }

    private fun redisStandaloneConfiguration(): RedisStandaloneConfiguration {
        // 2023.10.17[holywater]: hosts가 1개
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = "127.0.0.1"
        redisStandaloneConfiguration.port = 6379
        return redisStandaloneConfiguration
    }

}