package com.microservices.studies

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(private val customerHandler: CustomerHandler) {

  @Bean
  fun customerRoutes() = router {
    "/functional".nest {
      "/customers".nest {
        GET("/{id}", customerHandler::get)
      }
    }
  }

}