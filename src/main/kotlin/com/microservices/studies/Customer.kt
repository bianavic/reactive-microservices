package com.microservices.studies

import com.fasterxml.jackson.annotation.JsonInclude

data class Customer(var id: Int = 0, var name: String = "", var telephone: Telephone? = null) {
    data class Telephone(var countryCode: String = "", var telephone: String = "")
}
