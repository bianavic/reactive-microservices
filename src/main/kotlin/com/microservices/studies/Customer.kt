package com.microservices.studies

// provide equals(), hashCode(), toString(), componentN() functions, copy()
data class Customer(var id: Int = 0, var name: String = "", var telephone: Telephone) {
    data class Telephone(var countryCode: String = "", var telephone: String = "")
}
