package com.microservices.studies

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {
    @RequestMapping(value = arrayOf("/customer"), method = arrayOf(RequestMethod.GET))
    fun getCustomer() = "hello world from a controller"

}