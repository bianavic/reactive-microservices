package com.microservices.studies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {

    @Autowired
    lateinit var customers : ConcurrentHashMap<Int, Customer>

    @RequestMapping(value = arrayOf("/customer/{id}"), method = arrayOf(RequestMethod.GET))
    fun getCustomer(@PathVariable id : Int) = customers[id]

    // filter customer list by name
    @RequestMapping(value = arrayOf("/customers"), method = arrayOf(RequestMethod.GET))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList()

    // @RequestBody = means it is sending a object
    @RequestMapping(value = arrayOf("/customer"), method = arrayOf(RequestMethod.POST))
    fun createCustomer(@RequestBody customer: Customer) {
        customers[customer.id] = customer
    }

    @RequestMapping(value = arrayOf("/customer/{id}"), method = arrayOf(RequestMethod.DELETE))
    fun deleteCustomer(@PathVariable id: Int) = customers.remove(id)
}