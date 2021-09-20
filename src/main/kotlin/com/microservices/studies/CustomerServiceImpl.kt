package com.microservices.studies

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {

    // add a map and initialization as we do in the past on our bean to have our customers in this class
    companion object {
        val initialCustomer = arrayOf(
            Customer(1, "Jane Doe"),
            Customer(2, "John Doe"),
            Customer(3, "Mary Ann", Customer.Telephone("+55", "777777777"))
        )
    }

    val customers = ConcurrentHashMap<Int, Customer>(initialCustomer.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun searchCustomers(nameFilter: String) =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toFlux()

    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> =
        customerMono.map {
            customers[it.id] = it
        }

}