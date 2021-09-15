package com.microservices.studies

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.kotlin.core.publisher.toMono

/**
 * @author fferlin
 */

@Component
class CustomerHandler {

    fun get(serverRequest: ServerRequest) =
        ok().body(Customer(1, "Jane Doe").toMono(),
        Customer::class.java)
}