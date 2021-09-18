package com.microservices.studies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class CustomerController {

    @Autowired
    private lateinit var customerService: CustomerService

    @GetMapping(value = arrayOf("/customer/{id}"))
    fun getCustomer(@PathVariable id : Int) =
        ResponseEntity(customerService.getCustomer(id), HttpStatus.OK)

    @GetMapping(value = arrayOf("/customers"))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        ResponseEntity(customerService.searchCustomer(nameFilter), HttpStatus.OK)

    // @RequestBody = means it is sending a object
    // set Unit = equivalent to void type
    // indicate our response will be the result of creating the customer through another publisher
    // that willl be created in our service since createCustomer returns a Mono
    @PostMapping(value = arrayOf("/customer"))
    fun createCustomer(@RequestBody customerMono: Mono<Customer>) =
        ResponseEntity(customerService.createCustomer(customerMono), HttpStatus.CREATED)

}