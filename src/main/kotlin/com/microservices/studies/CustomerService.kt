package com.microservices.studies

// These operations don't expose how customers are stored/saved or searched, they are purely the interfac
interface CustomerService {

    fun getCustomer(id: Int) : Customer?
    fun createCustomer(customer: Customer)
    fun deleteCustomer(id: Int)
    fun updateCustomer(id: Int, customer: Customer)
    fun searchCustomer(nameFilter: String): List<Customer>

}