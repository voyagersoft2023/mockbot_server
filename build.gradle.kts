import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.21"
	kotlin("kapt") version "1.8.21"
	idea
}

group = "com.voyagersoft"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

allOpen {
	// Spring Boot 3.0.0
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// 2023.10.13[holywater]: AOP
	implementation("org.springframework.boot:spring-boot-starter-aop:3.1.4")

	// 2023.10.13[holywater]: Logger
	implementation("io.github.microutils:kotlin-logging:3.0.5")

	// 2023.10.13[holywater]: JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.4")

	// 2023.10.17[holywater]: Redis & commons-pool & Lettuce
	implementation("org.springframework.data:spring-data-redis:3.1.4")
	implementation("org.apache.commons:commons-pool2:2.12.0")
	implementation("io.lettuce:lettuce-core:6.2.6.RELEASE")

	// 2023.10.13[holywater]: DB 종류
	implementation("org.postgresql:postgresql:42.6.0")

	// 2023.10.13[holywater]: 스프링 시큐리티
	implementation("org.springframework.boot:spring-boot-starter-security:3.1.4")

	// 2023.10.13[holywater]: JWT 토큰 & javax(java11버전 이상부터 꼭 추가해야됨)
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	implementation("javax.xml.bind:jaxb-api:2.3.1")

	// 2023.10.16[shiningtrue]: Json
	implementation("com.google.code.gson:gson:2.10.1")
	implementation("com.googlecode.json-simple:json-simple:1.1.1")

	// 2023.10.19[shiningtrue]: Xss StringEscapeUtils 사용하기 위함
	implementation("org.apache.commons:commons-text:1.10.0")

	// 2023.10.23[shiningtrue]: QueryDSL 설정
	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt ("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt ("jakarta.annotation:jakarta.annotation-api")
	kapt ("jakarta.persistence:jakarta.persistence-api")

	// 2023.11.01[shiningtrue]: ELK
	implementation("net.logstash.logback:logstash-logback-encoder:7.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

idea {
	module {
		val kaptMain = file("build/generated/source/kapt/main")
		sourceDirs.add(kaptMain)
		generatedSourceDirs.add(kaptMain)
	}
}