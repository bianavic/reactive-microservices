package com.microservices.studies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class StudiesApplication

fun main(args: Array<String>) {
	runApplication<StudiesApplication>(*args)
}
