package com.voyagersoft.mockbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MockbotServerApplication

fun main(args: Array<String>) {
    runApplication<MockbotServerApplication>(*args)
}
