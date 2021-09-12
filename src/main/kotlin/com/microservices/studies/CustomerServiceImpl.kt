package com.microservices.studies

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    // add a map and initialization as we do in the past on our bean to have our customers in this class
    companion object {
        val initialCustomer = arrayOf(Customer(1, "Jane Doe", Customer.Telephone("+55", "9999999999")),
            Customer(2, "John Doe", Customer.Telephone("+55", "888888888")),
            Customer(3, "Mary Ann", Customer.Telephone("+55", "777777777"))
        )
    }
    val customers = ConcurrentHashMap<Int, Customer>(initialCustomer.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        deleteCustomer(id)
        createCustomer(customer)
    }

    override fun searchCustomer(nameFilter: String): List<Customer> =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList()

}