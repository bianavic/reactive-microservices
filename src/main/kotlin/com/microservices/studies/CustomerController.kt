package com.microservices.studies

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping(value = arrayOf("/customer/{id}"))
    fun getCustomer(@PathVariable id : Int): ResponseEntity<Customer?> {
        val customer = customerService.getCustomer(id)
        val status = if (customer == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(customer, status)
    }

    // filter customer list by name
    @GetMapping(value = arrayOf("/customers"))
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customerService.searchCustomer(nameFilter)

    // @RequestBody = means it is sending a object
    // set Unit = equivalent to void type
    @PostMapping(value = arrayOf("/customer"))
    fun createCustomer(@RequestBody customer: Customer):  ResponseEntity<Unit> {
        customerService.createCustomer(customer)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @DeleteMapping(value = arrayOf("/customer/{id}"))
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping(value = arrayOf("/customer/{id}"))
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) {
        customerService.updateCustomer(id, customer)
    }
}