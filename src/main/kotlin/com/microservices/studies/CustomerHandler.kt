package com.microservices.studies

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.bodyToMono

/**
 * @author fferlin
 *
 * HANDLER perform the logic to transform a concrete request into a response
 */

@Component
class CustomerHandler(val customerService: CustomerService) {

    fun get(serverRequest: ServerRequest) =
        customerService.getCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap { ok().body(fromObject(it)) }
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) =
        ok().body(customerService.searchCustomer(serverRequest.queryParam("nameFilter").orElse("")), Customer::class.java)

    fun create(serverRequest: ServerRequest) =
        customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
            created(java.net.URI.create("/functional/customer/${it.id}")).build()
        }.onErrorResume(Exception::class.java) {
            badRequest().body(fromObject(ErrorResponse("error creating customer",
                it.message ?: "error")))
        }
}