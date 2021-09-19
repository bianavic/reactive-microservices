package com.microservices.studies

class CustomerExistException(override val message: String) : Exception(message)