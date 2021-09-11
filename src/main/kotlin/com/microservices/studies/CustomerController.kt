package com.microservices.studies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Filter

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
}