package com.microservices.studies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StudiesApplication

fun main(args: Array<String>) {
	runApplication<StudiesApplication>(*args)
}
