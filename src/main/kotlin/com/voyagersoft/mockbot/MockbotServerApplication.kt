package com.voyagersoft.mockbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class MockbotServerApplication

fun main(args: Array<String>) {
    runApplication<MockbotServerApplication>(*args)
}
