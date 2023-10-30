package com.voyagersoft.mockbot.config.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import java.util.stream.Collectors





@Aspect
@Component
class ParamAspect {

    private val log = KotlinLogging.logger{ }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    fun test(pjp: ProceedingJoinPoint): Any {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        log.info("URL: ${request.requestURL}")
        paramMapToString(request.parameterMap)

        return pjp.proceed()
        // ((response as ResponseEntity<*>).body as ResponseStructure)
    }

    private fun paramMapToString(paraStringMap: Map<String, Array<String>>): String {
        return paraStringMap.entries.stream()
            .map<Any> { (key, value): Map.Entry<String, Array<String>> ->
                log.info("Param: ${key}${value[0]}")
            }
            .collect(Collectors.toList()).toString()
    }

}