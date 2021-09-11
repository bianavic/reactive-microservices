package com.microservices.studies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class StudiesApplication {
	companion object {
		val initialCustomers = arrayOf(Customer(1, "Jane Doe"),
		Customer(2, "John Doe"),
		Customer(3, "Mary Ann")
		)
	}

	@Bean
	fun customers() = ConcurrentHashMap<Int,Customer>(initialCustomers.associateBy(Customer::id))
}

fun main(args: Array<String>) {
	runApplication<StudiesApplication>(*args)
}
