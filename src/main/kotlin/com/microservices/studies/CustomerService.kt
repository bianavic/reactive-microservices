package com.microservices.studies

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

// These operations don't expose how customers are stored/saved or searched, they are purely the interfac
interface CustomerService {

    // return a publisher for a single customer
    fun getCustomer(id: Int) : Mono<Customer>
    fun createCustomer(customerMono: Mono<Customer>) : Mono<*>
    fun searchCustomer(nameFilter: String): Flux<Customer>

}